package com.hx.base.util;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by hx on 16-9-19.
 */
public class JsonUtilsTest {
  @Test
  public void test() throws IOException {
    String data ="\\\"\"123\"45\n\"";
//    String data ="";
//    System.out.println(JsonUtils.toOneLine(data));
//    File file = new File("/f/JavaHome/code/MyProjects/hx-base/src/test/java/com/hx/base/util/sparkprocess.xml");
//    File file = new File("/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLibrary/magiclibrary-common-operator/src/main/resources/description/SparkBatchEngineDescription.xml");

    String oneLine=JsonUtils.toOneLine(new FileInputStream("/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLamp/magiclamp-server/processes/projectprocess.xml"));
    System.out.println(oneLine);
//    String pretty=JsonUtils.oneLine2Pretty(oneLine);
//    System.out.println(pretty);
//    System.out.println(JsonUtils.toOneLine(new FileInputStream(file)));
//    ResourceLoader rl = new DefaultResourceLoader();
//    Resource r=rl.getResource(file.getAbsoluteFile().toString());
//    System.out.println(JsonUtils.toOneLine(r.getInputStream()));

//    System.out.println(JsonUtils.oneLine2Pretty("rr\\n12345\\t\\\"455555"));
//    System.out.println(JsonUtils.oneLine2Pretty("133333"));
//    System.out.println(JsonUtils.oneLine2Pretty(""));
//    System.out.println(JsonUtils.oneLine2Pretty("\""));
//    System.out.println(JsonUtils.oneLine2Pretty("<operator id=\\\"10001\\\" name=\\\"process\\\" class=\\\"com.topsec.ti.patronus.engine.spark.driver.SparkPipeline\\\">    <parametergroup            name=\\\"spark.config\\\">        <!--Spark程序运行配置-->        <parameter name=\\\"spark.master\\\">yarn</parameter>        <parameter                name=\\\"sparkHome\\\">/opt/cloudera/parcels/CDH/lib/spark        </parameter>        <parameter                name=\\\"engineJarPath\\\">/home/patronus/classpath/patronus-engine-spark-1.0-SNAPSHOT.jar        </parameter>        <parameter                name=\\\"spark.dirver.memory\\\">512m        </parameter>        <parameter                name=\\\"spark.executor.memory\\\">512m        </parameter>        <parametertable name=\\\"extra.config\\\">            <head>                <parameter name=\\\"name\\\" valias=\\\"字段名\\\"/>                <parameter name=\\\"value\\\" valias=\\\"值\\\"/>            </head>            <parameterrow                    name=\\\"spark.sql.inMemoryColumnarStorage.compressed\\\" value=\\\"true\\\"></parameterrow>            <parameterrow                    name=\\\"spark.sql.autoBroadcastJoinThreshold\\\" value=\\\"10485760\\\"></parameterrow>        </parametertable>    </parametergroup>    <parametergroup            name=\\\"spark.engine.config\\\">        <parameter                name=\\\"spark.sql.shuffle.partitions\\\">200        </parameter>        <!--Spark Engine配置-->        <parameter                name=\\\"spark.engine.env.type\\\">batch        </parameter>        <parameter                name=\\\"spark.engine.cache.enable\\\">true        </parameter>        <parameter                name=\\\"spark.engine.parallelism\\\">4        </parameter>    </parametergroup>    <operator            id=\\\"10002\\\" name=\\\"CSVReader\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.reader.CSVReader\\\">        <parameter name=\\\"path\\\">/tmp/1</parameter>        <outport                id=\\\"outport1\\\" name=\\\"过程输出端口1\\\"/>    </operator>    <operator            id=\\\"10003\\\" name=\\\"FixString\\\" class=\\\"com.topsec.ti.operator.masking.base.impl.FixString\\\">        <parameter                name=\\\"fixString\\\">fixString        </parameter>        <parameter name=\\\"column\\\">0</parameter>        <parameter name=\\\"start\\\">0</parameter>        <parameter name=\\\"length\\\">1</parameter>        <inport                id=\\\"inport1\\\" name=\\\"过程输入端口1\\\"/>        <outport id=\\\"outport1\\\" name=\\\"过程输出端口1\\\"/>    </operator>    <operator        id=\\\"10004\\\" name=\\\"CSVWriter\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.writer.CSVWriter\\\">        <parameter                name=\\\"path\\\">            /tmp/2        </parameter>        <inport            id=\\\"inport1\\\" name=\\\"xxx\\\"/>    </operator>    <connect            from=\\\"10002.outport1\\\" to=\\\"10003.inport1\\\"/>    <connect from=\\\"10003.outport1\\\" to=\\\"10004.inport1\\\"/></operator>"));
//

  }

  @Test
  public void convert2OneLineTest() throws IOException {
    String file="/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLamp/magiclamp-server/processes/projectprocess.xml";
    file= "/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLibrary/magiclibrary-common-operator/process/preferenceTest.xml";
    file="/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLibrary/magiclibrary-common-operator/process/process.xml";
    file="/f/JavaHome/IdeaCode/topinsight/MagicLampII/MagicLibrary/magiclibrary-common-operator/process/preferenceTest.xml";
    String oneLine=JsonUtils.toOneLine(new FileInputStream(file));
    System.out.println(oneLine);



  }
  @Test
  public void oneLine2PrettyTest() throws IOException {
    String string="<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\\n<operator id=\\\"376878F4-1654-47C0\\\" class=\\\"com.topsec.ti.patronus.engine.spark.driver.SparkPipeline\\\" name=\\\"Sparkshell容器算子\\\" version=\\\"0.0\\\" type=\\\"container\\\"><parametergroup name=\\\"spark.config\\\"><parameter name=\\\"spark.app.name\\\">Spark Batch Job<\\/parameter><parameter name=\\\"spark.master\\\">yarn-cluster<\\/parameter><parameter name=\\\"spark.yarn.queue\\\">default<\\/parameter><parameter name=\\\"proxy-user\\\"><\\/parameter><parameter name=\\\"spark.memory.fraction\\\">0.75<\\/parameter><parameter name=\\\"spark.memory.storageFraction\\\">0.5<\\/parameter><parameter name=\\\"spark.driver.cores\\\">2<\\/parameter><parameter name=\\\"spark.driver.memory\\\">512m<\\/parameter><parameter name=\\\"spark.yarn.driver.memoryOverhead\\\">384m<\\/parameter><parameter name=\\\"spark.executor.memory\\\">512m<\\/parameter><parameter name=\\\"spark.executor.cores\\\">2<\\/parameter><parameter name=\\\"spark.yarn.executor.memoryOverhead\\\">384m<\\/parameter><parameter name=\\\"spark.executor.instances\\\">2<\\/parameter><parameter name=\\\"spark.default.parallelism\\\">2<\\/parameter><\\/parametergroup><parametergroup name=\\\"spark.engine.config\\\"><parameter name=\\\"spark.engine.env.type\\\">batch<\\/parameter><parameter name=\\\"spark.engine.autocache.enable\\\">true<\\/parameter><parameter name=\\\"spark.engine.cache.autocache.storagelevel\\\">true<\\/parameter><parameter name=\\\"spark.engine.parallelism\\\">2<\\/parameter><\\/parametergroup><parameter name=\\\"container\\\">SparkPipeline<\\/parameter><parameter name=\\\"containerClass\\\">com.topsec.ti.patronus.engine.spark.driver.SparkPipeline<\\/parameter><parameter name=\\\"serverId\\\">123456789<\\/parameter><parameter name=\\\"process\\\"><\\/parameter><operator id=\\\"7CB929E3-F6DE-47BC\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.reader.CSVReader\\\" name=\\\"CSV文件读取\\\" version=\\\"0.0\\\" x=\\\"50.0\\\" y=\\\"127.0\\\" type=\\\"reader.file\\\"><parameter name=\\\"path\\\">hdfs://192.168.59.11:8020/user/root/spark/csvinput.csv<\\/parameter><parameter name=\\\"delimter\\\">,<\\/parameter><outport id=\\\"outport1\\\" name=\\\"过程输出端口1\\\" datasetType=\\\"/base\\\"/><\\/operator><operator id=\\\"AEA36AC9-86C2-4E79\\\" class=\\\"com.topsec.ti.patronus.test.stuboperator.writer.CSVWriter\\\" name=\\\"CSV文件写入\\\" version=\\\"0.0\\\" x=\\\"376.0\\\" y=\\\"124.0\\\" type=\\\"writer.file\\\"><parameter name=\\\"path\\\">hdfs://192.168.59.11:8020/user/root/spark/output/<\\/parameter><inport id=\\\"inport1\\\" name=\\\"过程输入端口1\\\" datasetType=\\\"/base\\\" option=\\\"false\\\"/><\\/operator><connect from=\\\"7CB929E3-F6DE-47BC.outport1\\\" to=\\\"AEA36AC9-86C2-4E79.inport1\\\"/><\\/operator>";
    String oneLine=JsonUtils.oneLine2Pretty(new FileInputStream("/f/JavaHome/code/MyProjects/hx-base/src/test/resources/file.xml"));
    System.out.println(oneLine);



  }

}
