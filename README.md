# ExpressionBuilder
# 表达式生成器
## 1. 功能
* 生成 小学四则运算题目、答案、对比其他答案

* 运算数：自然数、真分数

* 符号：+ - * / ( ) =

## 2. 参数
* -n 10  
控制表达式数量，将生成10个表达式，默认n为10
* -r 10  
控制运算数范围，将生成[0,10]的运算数，默认r为20
* -e \<exercisefile\> -a \<answerfile\>   
计算出\<exercisefile\>中表达式的答案，并与\<answerfile\>做对比，输出对比结果

#### Java source file
* 存放java源文件
#### ExpressionBuilder.exe
* 编译好的exe文件，未打包jdk，需要java环境运行