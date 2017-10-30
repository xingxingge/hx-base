package com.hx.base.net.httpclient;

import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HTTP工具类，封装HttpClient4.3.x来对外提供简化的HTTP请求
 * @author   yangjian1004
 * @Date     Aug 5, 2014
 */
public class HttpHelper {

  private static Integer socketTimeout            = 50;
  private static Integer connectTimeout           = 6000;
  private static Integer connectionRequestTimeout = 50;

  /**
   * 使用Get方式 根据URL地址，获取ResponseContent对象
   *
   * @param url
   *            完整的URL地址
   * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
   */
  public static ResponseContent getUrlRespContent(String url) {
    HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
    ResponseContent response = null;
    try {
      response = hw.getResponse(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  /**
   * 使用Get方式 根据URL地址，获取ResponseContent对象
   *
   * @param url
   *            完整的URL地址
   * @param urlEncoding
   *            编码，可以为null
   * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
   */
  public static ResponseContent getUrlRespContent(String url, String urlEncoding) {
    HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
    ResponseContent response = null;
    try {
      response = hw.getResponse(url, urlEncoding);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  /**
   * 将参数拼装在url中，进行post请求。
   *
   * @param url
   * @return
   */
  public static ResponseContent postUrl(String url) {
    HttpClientWrapper hw = new HttpClientWrapper();
    ResponseContent ret = null;
    try {
      setParams(url, hw);
      ret = hw.postNV(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  private static void setParams(String url, HttpClientWrapper hw) {
    String[] paramStr = url.split("[?]", 2);
    if (paramStr == null || paramStr.length != 2) {
      return;
    }
    String[] paramArray = paramStr[1].split("[&]");
    if (paramArray == null) {
      return;
    }
    for (String param : paramArray) {
      if (param == null || "".equals(param.trim())) {
        continue;
      }
      String[] keyValue = param.split("[=]", 2);
      if (keyValue == null || keyValue.length != 2) {
        continue;
      }
      hw.addNV(keyValue[0], keyValue[1]);
    }
  }

  /**
   * 上传文件（包括图片）
   *
   * @param url
   *            请求URL
   * @param paramsMap
   *            参数和值
   * @return
   */
  public static ResponseContent postEntity(String url, Map<String, Object> paramsMap) {
    HttpClientWrapper hw = new HttpClientWrapper();
    ResponseContent ret = null;
    try {
      setParams(url, hw);
      Iterator<String> iterator = paramsMap.keySet().iterator();
      while (iterator.hasNext()) {
        String key = iterator.next();
        Object value = paramsMap.get(key);
        if (value instanceof File) {
          FileBody fileBody = new FileBody((File) value);
          hw.getContentBodies().add(fileBody);
        } else if (value instanceof byte[]) {
          byte[] byteVlue = (byte[]) value;
          ByteArrayBody byteArrayBody = new ByteArrayBody(byteVlue, key);
          hw.getContentBodies().add(byteArrayBody);
        } else {
          if (value != null && !"".equals(value)) {
            hw.addNV(key, String.valueOf(value));
          } else {
            hw.addNV(key, "");
          }
        }
      }
      ret = hw.postEntity(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * 使用post方式，发布对象转成的json给Rest服务。
   *
   * @param url
   * @param jsonBody
   * @return
   */
  public static ResponseContent postJsonEntity(String url, String jsonBody) {
    return postEntity(url, jsonBody, "application/json");
  }

  /**
   * 使用post方式，发布对象转成的xml给Rest服务
   *
   * @param url
   *            URL地址
   * @param xmlBody
   *            xml文本字符串
   * @return ResponseContent 如果发生异常则返回空，否则返回ResponseContent对象
   */
  public static ResponseContent postXmlEntity(String url, String xmlBody) {
    return postEntity(url, xmlBody, "application/xml");
  }

  private static ResponseContent postEntity(String url, String body, String contentType) {
    HttpClientWrapper hw = new HttpClientWrapper();
    ResponseContent ret = null;
    try {
      hw.addNV("body", body);
      ret = hw.postNV(url, contentType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  public static void main(String[] args) {
//    testGet();
//    testUploadFile();
    testPost();
  }

  //test
  public static void testGet() {
    String url = "http://www.baidu.com/";
    ResponseContent responseContent = getUrlRespContent(url);
    try {
      System.out.println(responseContent.getContent());
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
  public static void testPost() {
    String body=" {\n" +
            "   \"commandId\": \"a22c402c-1aaa-41a8-a56b-7db722784929\",\n" +
            "   \"programId\": \"d95e6be0-c81f-40cc-a030-59a3efb1bee1\",\n" +
            "   \"serviceInstanceId\": \"35853176-b7dd-4bf8-848c-191222ee6e86\",\n" +
            "   \"parameters\":    {\n" +
            "     \"process\": \"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\\n<operator id=\\\"376878F4-1654-47C0\\\" class=\\\"com.topsec.ti.patronus.engine.spark.driver.SparkPipeline\\\" name=\\\"Sparkshell容器算子\\\" version=\\\"0.0\\\" type=\\\"system\\\" provider=\\\"topsec\\\"><parameter name=\\\"container\\\">SparkPipeline<\\/parameter><parameter name=\\\"containerClass\\\">com.topsec.ti.patronus.engine.spark.driver.SparkPipeline<\\/parameter><parametergroup name=\\\"spark.config\\\"><parameter name=\\\"spark.app.name\\\">Spark Batch Job<\\/parameter><parameter name=\\\"spark.master\\\">yarn-cluster<\\/parameter><parameter name=\\\"spark.yarn.queue\\\">default<\\/parameter><parameter name=\\\"proxy-user\\\"><\\/parameter><parameter name=\\\"spark.memory.fraction\\\">0.75<\\/parameter><parameter name=\\\"spark.memory.storageFraction\\\">0.5<\\/parameter><parameter name=\\\"spark.driver.cores\\\">1<\\/parameter><parameter name=\\\"spark.driver.memory\\\">512m<\\/parameter><parameter name=\\\"spark.yarn.driver.memoryOverhead\\\">384<\\/parameter><parameter name=\\\"spark.executor.memory\\\">512m<\\/parameter><parameter name=\\\"spark.executor.cores\\\">1<\\/parameter><parameter name=\\\"spark.yarn.executor.memoryOverhead\\\">384<\\/parameter><parameter name=\\\"spark.executor.instances\\\">2<\\/parameter><parameter name=\\\"spark.default.parallelism\\\">2<\\/parameter><\\/parametergroup><parametergroup name=\\\"ioposition\\\"><parameter name=\\\"ioposition\\\">{\\\"ioType\\\":\\\"OUTPUT\\\",\\\"operatorId\\\":\\\"AEA36AC9-86C2-4E79\\\",\\\"operatorName\\\":\\\"CSV文件写入\\\",\\\"operatorClass\\\":\\\"com.topsec.ti.patronus.test.stuboperator.writer.CSVWriter\\\",\\\"rowCount\\\":0,\\\"size\\\":0,\\\"processJobInstanceId\\\":\\\"123\\\",\\\"storageSource\\\":{\\\"dataStorageType\\\":\\\"DATABASE\\\",\\\"storageConnType\\\":\\\"DEFAULT\\\",\\\"port\\\":0,\\\"spaceSize\\\":0,\\\"configs\\\":[]}}<\\/parameter><parameter name=\\\"rowCount\\\"><\\/parameter><parameter name=\\\"size\\\"><\\/parameter><parameter name=\\\"mediatype\\\"><\\/parameter><parameter name=\\\"target\\\"><\\/parameter><parameter name=\\\"activemqUrl\\\">tcp://localhost:61616<\\/parameter><parameter name=\\\"activemqTopic\\\">com.topsec.ti.magiclamp.job.ioposition<\\/parameter><\\/parametergroup><operator id=\\\"7CB929E3-F6DE-47BC\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.reader.CSVReader\\\" name=\\\"CSV文件读取\\\" version=\\\"0.0\\\" x=\\\"31.0\\\" y=\\\"139.0\\\" type=\\\"input\\\" provider=\\\"topsec\\\"><parameter name=\\\"path\\\">/tmp/1<\\/parameter><parameter name=\\\"delimter\\\">,<\\/parameter><outport id=\\\"outport1\\\" name=\\\"过程输出端口1\\\" datasetType=\\\"/base\\\"/><\\/operator><operator id=\\\"AEA36AC9-86C2-4E79\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.writer.CSVWriter\\\" name=\\\"CSV文件写入\\\" version=\\\"0.0\\\" x=\\\"422.0\\\" y=\\\"135.0\\\" type=\\\"output\\\" provider=\\\"topsec\\\"><parameter name=\\\"path\\\">/tmp/2<\\/parameter><inport id=\\\"inport1\\\" name=\\\"过程输入端口1\\\" datasetType=\\\"/base\\\" option=\\\"false\\\"/><outport id=\\\"outport1\\\" name=\\\"过程输入端口1\\\" datasetType=\\\"/base\\\"/><\\/operator><operator id=\\\"v-OgyiEgRV-dmeV0CH9NdA\\\" class=\\\"com.topsec.ti.magiclamp.patronous.IoReporter\\\" name=\\\"v-OgyiEgRV-dmeV0CH9NdA\\\" provider=\\\"topsec\\\"><parameter name=\\\"ioposition\\\">{\\\"ioType\\\":\\\"OUTPUT\\\",\\\"operatorId\\\":\\\"AEA36AC9-86C2-4E79\\\",\\\"operatorName\\\":\\\"CSV文件写入\\\",\\\"operatorClass\\\":\\\"com.topsec.ti.patronus.test.stuboperator.writer.CSVWriter\\\",\\\"rowCount\\\":0,\\\"size\\\":0,\\\"processJobInstanceId\\\":\\\"123\\\",\\\"storageSource\\\":{\\\"dataStorageType\\\":\\\"DATABASE\\\",\\\"storageConnType\\\":\\\"DEFAULT\\\",\\\"port\\\":0,\\\"spaceSize\\\":0,\\\"configs\\\":[]}}<\\/parameter><parameter name=\\\"rowCount\\\"><\\/parameter><parameter name=\\\"size\\\"><\\/parameter><parameter name=\\\"mediatype\\\"><\\/parameter><parameter name=\\\"target\\\"><\\/parameter><parameter name=\\\"activemqUrl\\\">tcp://localhost:61616<\\/parameter><parameter name=\\\"activemqTopic\\\">com.topsec.ti.magiclamp.job.ioposition<\\/parameter><inport id=\\\"inport1\\\" name=\\\"inport1\\\" datasetType=\\\"/base\\\" option=\\\"false\\\"/><\\/operator><connect from=\\\"7CB929E3-F6DE-47BC.outport1\\\" to=\\\"AEA36AC9-86C2-4E79.inport1\\\"/><connect from=\\\"AEA36AC9-86C2-4E79.outport1\\\" to=\\\"v-OgyiEgRV-dmeV0CH9NdA.inport1\\\"/><\\/operator>\",\n" +
            "     \"context\": \"<context><parameter name=\\\"hive.server\\\">192.168.1.1:22<\\/parameter><parametertable name=\\\"temp.path\\\"><head><parameter name=\\\"value\\\"/><\\/head><parameterrow value=\\\"/tmp/patronus1\\\"><\\/parameterrow><parameterrow value=\\\"/tmp/patronus2\\\"><\\/parameterrow><\\/parametertable><\\/context>\"\n" +
            "   }\n" +
            " }";
    String url = "http://172.21.1.94:8080/jobs";
    ResponseContent responseContent = postJsonEntity(url,body);
    try {
      System.out.println(responseContent.getContent());
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  //test
  public static void testUploadFile() {
    try {
      String url = "http://192.168.76.50:8087/Pensieve/resources/stroageFile/uploadByUFL";
      Map<String, Object> paramsMap = new HashMap<String, Object>();
      paramsMap.put("ufl", "hdfs://hdfs1/csv.csv");
      paramsMap.put("password", "jj");
      paramsMap.put("file", new File("/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLibrary/magiclibrary-common-operator/src/test/resources/file/csv.csv"));
      ResponseContent ret = postEntity(url, paramsMap);
      System.out.println(ret.getContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
