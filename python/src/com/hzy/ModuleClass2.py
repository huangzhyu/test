'''
Created on 2015-7-31

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
        print(self.name)




if  __name__=='__main__':
    myclass=MyClass("xiaoming")
    getattr(myclass, 'printName').__call__()
#     myclass.printName()
    
    
    