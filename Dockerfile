# syntax=docker/dockerfile:1

##引入jdk8

FROM openjdk:8-jre

##定义执行目录
WORKDIR /home

#把编译后的jar包复制到home目录下
ADD target/budget-0.0.1-SNAPSHOT.jar budget-0.0.1-SNAPSHOT.jar

##设置镜像区时
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

##设置Volume卷积，可以通过docker -v挂接到对应的目录或者挂接NFS
##如：-v ${Linux path}:/home/logs
VOLUME ["/home/logs"]

##JVM 参数命令
##  MAXHEAPSIZE---->最大堆内存
##  MINHEAPSIZE---->最小堆内存
##  OTHERJVMCMD---->其他JVM命令，如是否使用G1收集器等
ENV MAXHEAPSIZE=-Xmx1024m
ENV MINHEAPSIZE=-Xms126m
ENV OTHERJVMCMD=""
ENV PORT=17600
#暴露端口
EXPOSE ${PORT}

##CMD执行，通过docker run -d挂起程序
CMD java -jar ${MAXHEAPSIZE} ${MINHEAPSIZE} -XX:+HeapDumpOnOutOfMemoryError ${OTHERJVMCMD} budget-0.0.1-SNAPSHOT.jar


