#coding=utf-8
'''
Created on 2015年7月31日

@author: huangzha
'''


class Attribute:
    
    def __init__(self,params=None):
        self.name=params
    
    
    
    def __repr__(self):
        '''
        相当于java中的toString方法
        '''
        return self.name
    
    
    
class myDic:
    def __init__(self):
        self.data={}
    
#     def __setattr__(self,dict,value):    
#         dict
# 
#     def __getattr__(self,key):
#         return self.data[key]
#     

    def __setitem__(self,key,value):
        '''
        对dict进行设置k-v时自动调用
        '''
        self.data[key]=value-90
        
        
        
    def __getitem__(self,key):
        '''
        从dict获取key对应的值时自动调用
        '''
        return self.data[key]
        


if __name__ == '__main__':
    attr = Attribute("a")
    attr2 = Attribute("b")
    attr3 = Attribute("c")
    print(attr)
    #根据name属性排序
    print(sorted([attr2,attr3,attr],key=lambda x:x.name))

#     [attr,attr2].sort(key=None, reverse=False)
    
#     md = myDic()
#     md["key"]=100;
#     print(md["key"])
#     
#     md2=myDic()
#     md2["key"]=100;
#     print(md2["key"])
    
    
    