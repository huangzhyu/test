'''
Created on 2015年7月31日

@author: huangzha
'''


class Attribute:
    
    def __init__(self,params=None):
        self.name=params
    
    
    def __setattr__(self,key,value):
        '''
        当设置类实例属性时自动调用，如j.name=5 就会调用__setattr__方法
        '''
        if value and key=="name":
            return object.__setattr__(self, key, value) 


    def __getattr__(self,key):
        '''
        当使用点号获取实例属性时，如果属性不存在就自动调用__getattr__方法
        '''
        return "not found"
    
    
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
    
    
    