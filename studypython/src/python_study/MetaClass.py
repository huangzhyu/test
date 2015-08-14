# coding=utf-8
'''
Created on 2015-8-11

@author: huangzha
'''



class ListMetaclass(type):
    
    pattern = r'''
                \{\{(?:
                (?P<escaped>\{\{)|
                (?P<named>[_a-z][_a-z0-9]*)\}\}|
                (?P<braced>[_a-z][_a-z0-9]*)\}\}|
                (?P<invalid>)
                )
                '''
    def __new__(cls, name, bases, attrs):
        print(cls)
#         print(len(attrs))
#         for k, v in attrs.iteritems():
#             print("%s=%s" % (k, v))
        attrs['add'] = lambda self, value: self.append(value)
        return type.__new__(cls, name, bases, attrs)
    

class MyList(list):
    __metaclass__ = ListMetaclass 
    
    
class subList(MyList):
    
    age = "Age"
    name = "123"
    
    def __init__(self, pname=None):
        print(subList.name)
    pass    



if __name__ == '__main__':
    mylist=MyList()
    sublist = subList()
    print(subList.__dict__)
