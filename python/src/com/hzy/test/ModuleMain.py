#coding=utf-8
'''
Created on 2015年7月31日

@author: huangzha
'''
#第一种导入方式
# import ModuleClass1
#import com.hzy.ModuleClass2



#第二种导入方式
#import __init__.py中配置的module
from com.hzy.test import *
from com.hzy import *



import copy

def retrospective(Object):
    obj = Object
    methodList = [ method for method in dir(obj) if callable(getattr(obj, method))]
    for m in methodList:
#         print(" ".join(getattr(obj, m).__doc__.split()))

#         processFunc = lambda s: " ".join(s.split())
#         print(processFunc(str(getattr(obj, m).__doc__)))
        
        print((lambda s: " ".join(s.split()))(str(getattr(obj, m).__doc__)))
        
    

def printList(switch):
    """
    根据switch的值返回对应的函数。如果为真，用分号连接字符串，如果为假，用空格连接字符串
    """
    func=switch and (lambda target: ";".join(target.split())) or (lambda target:" ".join(target.split()))
    
    return func

def finbonaci(initData):
    if initData<=1:
        return initData
    return finbonaci(initData-1) + finbonaci(initData-2)






if __name__ == "__main__":
    
#     a = "today"
#     b = "2015-07-30"
#     print("%s:%s" % (a, b))
#     
#     uid = "sa"
#     pwd = "secret"
#     userCount=10
#     print (pwd + " is not a good password for " + uid )
#     print ("%s is not a good password for %s" % (pwd, uid))
#     print ("Users connected: %d" % (userCount))
#     
#     my_str="1,2,3,4,5,6"
#     print(my_str.split(",",1))
#     
#     params = {"server":"mpilgrim", "database":"master", "uid":"sa", "pwd":"secret"}
#     obj = ["%s->%s"%(k,v) for k,v in params.items()]
#     print(obj)
#     print(type(obj))
#     
#     print(";".join(obj))
#     Object=type("")
#     retrospective(Object)
    
#     data="1 2 3 4"
#     func=printList(False)
#     print(func(data))
#     
#     print(finbonaci(6))

#    第一种导入方式的调用方法
#     m1 = ModuleClass1.MyClass("ModuleClass1.MyClass")
#     m1.printName()
#     
#     m2 = com.hzy.ModuleClass2.MyClass("ModuleClass2.MyClass")
#     m2.printName()
#    
#     
#     m3 = ModuleClass1.MyClass2("ModuleClass1.MyClass2")
#     m3.printName()
#     
#     m4 = com.hzy.ModuleClass2.MyClass2("ModuleClass2.MyClass2")
#     m4.printName()
    
#    第二种导入方式的调用方法
    m1 = ModuleClass1.MyClass("ModuleClass1.MyClass")
    m1.printName()
    
    
    m2 = ModuleClass2.MyClass("ModuleClass2.MyClass")
    m2.printName()
    
    m3 = ModuleClass1.MyClass2("ModuleClass1.MyClass2")
    m3.printName()
    
    m4 = ModuleClass2.MyClass2("ModuleClass2.MyClass2")
    m4.printName()
    
    m5=copy.copy(m4)
    m5.printName()
    
