'''
Created on 2015年7月31日

@author: huangzha
'''

class MyClass(object):
    '''
    classdocs
    '''

    def __init__(self, params):
        '''
        Constructor
        '''
        self.name=params
    
    def printName(self):
        print(self.name)
        
        
        
        
class MyClass2(object):
    '''
    classdocs
    '''

    def __init__(self, params):
        '''
        Constructor
        '''
        self.name=params
    
    def printName(self):
        print(self.__getattribute__("name"))
        
        
    
    
    
    
