package com.hx.base.util;

import com.hx.base.util.string.StringFormater;
import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * Helper class for resolving placeholders in texts. Usually applied to file
 * paths.
 *
 * <p>
 * A text may contain <code>${...}</code> placeholders, to be resolved as system
 * properties: e.g. <code>${user.dir}</code>.
 *
 * @see #PLACEHOLDER_PREFIX
 * @see #PLACEHOLDER_SUFFIX
 */
public abstract class PropertyPlaceholder {

  /** Prefix for system property placeholders: "${" */
  public static final String PLACEHOLDER_PREFIX = "${";

  /** Suffix for system property placeholders: "}" */
  public static final String PLACEHOLDER_SUFFIX = "}";

  /**
   * Resolve ${...} placeholders in the given text, replacing them with
   * corresponding system property values.
   *
   * @param text
   *          the String to resolve
   * @return the resolved String
   * @see #PLACEHOLDER_PREFIX
   * @see #PLACEHOLDER_SUFFIX
   */
  public static String resolveSystemPlaceholders(String text) {
    return resolveSystemPlaceholders(text, PLACEHOLDER_PREFIX,
            PLACEHOLDER_SUFFIX);
  }

  public static String resolveSystemPlaceholders(String text, String prefix,
                                                 String suffix) {
    return resolveSystemPlaceholders(text, prefix, suffix, false);
  }

  public static String resolveSystemPlaceholders(String text, String prefix,
                                                 String suffix, boolean ignoreUnresolvablePlaceholders) {
    Validate.notNull(text);
    if (prefix == null) {
      prefix = PLACEHOLDER_PREFIX;
    }
    if (suffix == null) {
      suffix = PLACEHOLDER_SUFFIX;
    }
    PlaceholderResolver resolver = new SystemPlaceholderResolver(prefix,
            suffix, ignoreUnresolvablePlaceholders);
    return resolvePlaceholders(text, resolver);
  }

  public static String resolvePlaceholders(String text, Properties props) {
    return resolvePlaceholders(text, props, PLACEHOLDER_PREFIX,
            PLACEHOLDER_SUFFIX, false);
  }

  public static String resolvePlaceholders(String text, Properties props,
                                           String prefix, String suffix) {
    return resolvePlaceholders(text, props, prefix, suffix, false);
  }

  public static String resolvePlaceholders(String text, Properties props,
                                           String prefix, String suffix, boolean ignoreUnresolvablePlaceholders) {
    Validate.notNull(text);
    Validate.notNull(props);
    if (prefix == null) {
      prefix = PLACEHOLDER_PREFIX;
    }
    if (suffix == null) {
      suffix = PLACEHOLDER_SUFFIX;
    }
    PlaceholderResolver resolver = new PropertiesPlaceholderResolver(prefix,
            suffix, ignoreUnresolvablePlaceholders, props);
    return resolvePlaceholders(text, resolver);
  }

  public static String resolvePlaceholders(String text,
                                           Map<String, ? extends Object> props) {
    return resolvePlaceholders(text, props, PLACEHOLDER_PREFIX,
            PLACEHOLDER_SUFFIX, false);
  }

  public static String resolvePlaceholders(String text,
                                           Map<String, ? extends Object> props, String prefix, String suffix) {
    return resolvePlaceholders(text, props, prefix, suffix, false);
  }

  public static String resolvePlaceholders(String text,
                                           Map<String, ? extends Object> props, String prefix, String suffix,
                                           boolean ignoreUnresolvablePlaceholders) {
    Validate.notNull(text);
    Validate.notNull(props);
    if (prefix == null) {
      prefix = PLACEHOLDER_PREFIX;
    }
    if (suffix == null) {
      suffix = PLACEHOLDER_SUFFIX;
    }
    MapPlaceholderResolver resolver = new MapPlaceholderResolver(prefix,
            suffix, ignoreUnresolvablePlaceholders, props);
    return resolvePlaceholders(text, resolver);
  }

  public static String resolvePlaceholders(String text,
                                           PlaceholderResolver resolver) {
    Set<String> visitedPlaceholders = new HashSet<String>();
    return resolvePlaceholders(text, resolver, visitedPlaceholders);
  }

  protected static String resolvePlaceholders(String text,
                                              PlaceholderResolver resolver, Set<String> visitedPlaceholders) {
    StringBuffer buf = new StringBuffer(text);
    int startIndex = text.indexOf(resolver.getPrefix());
    while (startIndex != -1) {
      int endIndex = findPlaceholderEndIndex(buf, startIndex,
              resolver.getPrefix(), resolver.getSuffix());
      if (endIndex != -1) {
        String placeholder = buf.substring(startIndex
                + resolver.getPrefix().length(), endIndex);
        if (!visitedPlaceholders.add(placeholder)) {
          throw new IllegalArgumentException(StringFormater.format(
                  "Circular placeholder reference '{}' in property definitions",
                  placeholder));
        }
        // Recursive invocation, parsing placeholders contained in the
        // placeholder key.
        placeholder = resolvePlaceholders(placeholder, resolver);
        // Now obtain the value for the fully resolved key...
        String propVal = resolver.resolvePlaceholder(placeholder);
        if (propVal != null) {
          // Recursive invocation, parsing placeholders contained in
          // the
          // previously resolved placeholder value.
          propVal = resolvePlaceholders(propVal, resolver);
          buf.replace(startIndex, endIndex + resolver.getSuffix().length(),
                  propVal);
          startIndex = buf.indexOf(resolver.getPrefix(),
                  startIndex + propVal.length());
        } else if (resolver.isIgnoreUnresolvablePlaceholders()) {
          // Proceed with unprocessed value.
          startIndex = buf.indexOf(resolver.getPrefix(), endIndex
                  + resolver.getSuffix().length());
        } else {
          throw new IllegalArgumentException("Could not resolve placeholder '"
                  + placeholder + "'");
        }
        visitedPlaceholders.remove(placeholder);
      } else {
        startIndex = -1;
      }
    }
    return buf.toString();
  }

  private static int findPlaceholderEndIndex(CharSequence buf, int startIndex,
                                             String prefix, String suffix) {
    int index = startIndex + prefix.length();
    int withinNestedPlaceholder = 0;
    while (index < buf.length()) {
      if (substringMatch(buf, index, suffix)) {
        if (withinNestedPlaceholder > 0) {
          withinNestedPlaceholder--;
          index = index + suffix.length();
        } else {
          return index;
        }
      } else if (substringMatch(buf, index, prefix)) {
        withinNestedPlaceholder++;
        index = index + prefix.length();
      } else {
        index++;
      }
    }
    return -1;
  }

  /**
   * Test whether the given string matches the given substring at the given
   * index.
   *
   * @param str
   *          the original string (or StringBuffer)
   * @param index
   *          the index in the original string to start matching against
   * @param substring
   *          the substring to match at the given index
   */
  private static boolean substringMatch(CharSequence str, int index,
                                        CharSequence substring) {
    for (int j = 0; j < substring.length(); j++) {
      int i = index + j;
      if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
        return false;
      }
    }
    return true;
  }
  public static List<String> getPlaceholders(String text) {
    PlaceholderGetterResolver resolver = new PlaceholderGetterResolver(
            PLACEHOLDER_PREFIX, PLACEHOLDER_SUFFIX, true);
    resolvePlaceholders(text, resolver);
    return resolver.getPlaceholders();
  }

  public static List<String> getPlaceholders(String text, String prefix,
                                             String suffix) {
    PlaceholderGetterResolver resolver = new PlaceholderGetterResolver(prefix,
            suffix, true);
    resolvePlaceholders(text, resolver);
    return resolver.getPlaceholders();
  }




  public static interface PlaceholderResolver {

    String getPrefix();

    String getSuffix();

    boolean isIgnoreUnresolvablePlaceholders();

    String resolvePlaceholder(String placeholder);
  }

  public static abstract class AbstractPlaceholderResolver implements
          PlaceholderResolver {

    protected String prefix;
    protected String suffix;
    protected boolean ignoreUnresolvablePlaceholders;

    public AbstractPlaceholderResolver(String prefix, String suffix,
                                       boolean ignoreUnresolvablePlaceholders) {
      Validate.notEmpty(prefix, "prefix is empty!");
      Validate.notEmpty(suffix, "suffix is empty!");
      this.prefix = prefix;
      this.suffix = suffix;
      this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
    }

    @Override
    public String getPrefix() {
      return prefix;
    }

    @Override
    public String getSuffix() {
      return suffix;
    }

    @Override
    public boolean isIgnoreUnresolvablePlaceholders() {
      return ignoreUnresolvablePlaceholders;
    }
  }

  private static class PropertiesPlaceholderResolver extends
          AbstractPlaceholderResolver {
    protected Properties props;

    public PropertiesPlaceholderResolver(String prefix, String suffix,
                                         boolean ignoreUnresolvablePlaceholders, Properties props) {
      super(prefix, suffix, ignoreUnresolvablePlaceholders);
      this.props = props;
    }

    public String resolvePlaceholder(String placeholder) {
      return props.getProperty(placeholder);
    }
  }

  private static class SystemPlaceholderResolver extends
          AbstractPlaceholderResolver {

    public SystemPlaceholderResolver(String prefix, String suffix,
                                     boolean ignoreUnresolvablePlaceholders) {
      super(prefix, suffix, ignoreUnresolvablePlaceholders);
    }

    public String resolvePlaceholder(String placeholder) {
      String propVal = System.getProperty(placeholder);
      if (propVal == null) {
        propVal = System.getenv(placeholder);
      }
      return propVal;
    }
  }

  private static class MapPlaceholderResolver extends
          AbstractPlaceholderResolver {
    protected Map<String, ? extends Object> props;

    public MapPlaceholderResolver(String prefix, String suffix,
                                  boolean ignoreUnresolvablePlaceholders,
                                  Map<String, ? extends Object> props) {
      super(prefix, suffix, ignoreUnresolvablePlaceholders);
      this.props = props;
    }

    public String resolvePlaceholder(String placeholder) {
      return props.get(placeholder).toString();
    }
  }

  private static class PlaceholderGetterResolver extends
          AbstractPlaceholderResolver {

    private List<String> placeholders = new LinkedList<String>();

    public PlaceholderGetterResolver(String prefix, String suffix,
                                     boolean ignoreUnresolvablePlaceholders) {
      super(prefix, suffix, ignoreUnresolvablePlaceholders);
    }

    public String resolvePlaceholder(String placeholder) {
      if (!placeholders.contains(placeholder)) {
        placeholders.add(placeholder);
      }
      return placeholder;
    }

    public List<String> getPlaceholders() {
      return placeholders;
    }
  }
}
