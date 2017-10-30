package com.hx.base.util;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;

public abstract class PathUtils {
  public static final String FOLDER_SEPARATOR = "/";
  public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
  public static final String TOP_PATH = "..";
  public static final String CURRENT_PATH = ".";
  public static final char EXTENSION_SEPARATOR = '.';

  /**
   * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
   * "myfile.txt".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted filename, or <code>null</code> if none
   */
  public static String getFileName(String path) {
    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex == -1) {
      separatorIndex = path.lastIndexOf(WINDOWS_FOLDER_SEPARATOR);
    }
    return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
  }

  /**
   * Extract the path from the given path, e.g. "mypath/myfile.txt" ->
   * "mypath/".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted file's path, or <code>null</code> if none
   */
  public static String getFilePath(String path) {
    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex == -1) {
      separatorIndex = path.lastIndexOf(WINDOWS_FOLDER_SEPARATOR);
    }
    return (separatorIndex != -1 ? path.substring(0, separatorIndex + 1) : path);
  }

  /**
   * Extract the filename without extension from the given path, e.g.
   * "mypath/myfile.txt" -> "myfile".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted filename extension, or <code>null</code> if none
   */
  public static String getFileNameWithoutExtension(String path) {
    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex == -1) {
      separatorIndex = path.lastIndexOf(WINDOWS_FOLDER_SEPARATOR);
    }
    int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    if (sepIndex != -1 && sepIndex > separatorIndex) { // deal the relative path: ./xx
      return path.substring(separatorIndex + 1, sepIndex);
    }
    return path.substring(separatorIndex + 1);
  }

  /**
   * Extract the filename extension from the given path, e.g.
   * "mypath/myfile.txt" -> "txt".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted filename extension, or <code>null</code> if none
   */
  public static String getFileNameExtension(String path) {
    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex == -1) {
      separatorIndex = path.lastIndexOf(WINDOWS_FOLDER_SEPARATOR);
    }
    int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    if (sepIndex != -1 && sepIndex > separatorIndex) { // deal the relative path: ./xx
      return path.substring(sepIndex + 1);
    }
    return "";
  }

  /**
   * Strip the filename extension from the given path, e.g. "mypath/myfile.txt"
   * -> "mypath/myfile".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the path with stripped filename extension, or <code>null</code> if
   * none
   */
  public static String stripFileNameExtension(String path) {
    if (path == null) {
      return null;
    }
    int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
  }

  /**
   * Apply the given relative path to the given path, assuming standard Java
   * folder separation (i.e. "/" separators);
   *
   * @param path         the path to start from (usually a full file path)
   * @param relativePath the relative path to apply (relative to the full file path above)
   * @return the full file path that results from applying the relative path
   */
  public static String applyRelativePath(String path, String relativePath) {
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex == -1) {
      separatorIndex = path.lastIndexOf(WINDOWS_FOLDER_SEPARATOR);
    }
    if (separatorIndex != -1) {
      String newPath = path.substring(0, separatorIndex);
      if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
        newPath += FOLDER_SEPARATOR;
      }
      return newPath + relativePath;
    } else {
      return relativePath;
    }
  }

  /**
   * Normalize the path by suppressing sequences like "path/.." and inner simple
   * dots.
   * <p>
   * The result is convenient for path comparison. For other uses, notice that
   * Windows separators ("\") are replaced by simple slashes.
   *
   * @param path the original path
   * @return the normalized path
   */
  public static String cleanPath(String path) {
    if (path == null) {
      return null;
    }
    String pathToUse = StringUtils.replace(path, WINDOWS_FOLDER_SEPARATOR,
            FOLDER_SEPARATOR);

    // Strip prefix from path to analyze, to not treat it as part of the
    // first path element. This is necessary to correctly parse paths like
    // "file:core/../core/io/Resource.class", where the ".." should just
    // strip the first "core" directory while keeping the "file:" prefix.
    int prefixIndex = pathToUse.indexOf(":");
    String prefix = "";
    if (prefixIndex != -1) {
      prefix = pathToUse.substring(0, prefixIndex + 1);
      pathToUse = pathToUse.substring(prefixIndex + 1);
    }
    if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
      prefix = prefix + FOLDER_SEPARATOR;
      pathToUse = pathToUse.substring(1);
    }

    String[] pathArray = StringUtils.split(pathToUse, FOLDER_SEPARATOR);
    List<String> pathElements = new LinkedList<String>();
    int tops = 0;

    for (int i = pathArray.length - 1; i >= 0; i--) {
      String element = pathArray[i];
      if (CURRENT_PATH.equals(element)) {
        // Points to current directory - drop it.
      } else if (TOP_PATH.equals(element)) {
        // Registering top path found.
        tops++;
      } else {
        if (tops > 0) {
          // Merging path element with element corresponding to top path.
          tops--;
        } else {
          // Normal path element found.
          pathElements.add(0, element);
        }
      }
    }

    // Remaining top paths need to be retained.
    for (int i = 0; i < tops; i++) {
      pathElements.add(0, TOP_PATH);
    }
    return prefix + StringUtils.join(pathElements, FOLDER_SEPARATOR);
  }

  public static final String translatePath2CanonicalPath(String path) {
    if (path.charAt(path.length() - 1) != FOLDER_SEPARATOR.charAt(0)
            && path.charAt(path.length() - 1) != WINDOWS_FOLDER_SEPARATOR.charAt(0))
      return path + FOLDER_SEPARATOR;
    return path;
  }
}
