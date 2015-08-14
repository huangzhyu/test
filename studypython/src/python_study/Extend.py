'''
Created on 2015-8-5

@author: huangzha
'''

class Father(object):
    '''
    classdocs
    '''


    def __init__(self, params=None):
        '''
        Constructor
        '''
        print("this is father")
#         print(locals())
#         print(globals())
        print(dir(self))
        
        
        
        
class Son(Father):
    '''
    If subclass doesn't define __init__ then it will use its parent's
    '''      
    
    
#     def __init__(self):  
#         
#         print("This is son")
#         Father.__init__(self)
        
        


if __name__=="__main__":
    son = Son()
    
    
    
    
    
    
            