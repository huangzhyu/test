#coding=utf-8
'''
Created on 2015-08-12 09:26

@author: huangzha
'''
from multiprocessing.managers import BaseManager  
  
class QueueManager(BaseManager):  
    pass  
  

    
    
def connectServer():    
    QueueManager.register('get_result_queue')  
  
    server_addr = '127.0.0.1'  
    print('Connect to server %s...' % server_addr)  
    m = QueueManager(address=(server_addr, 5000), authkey='abc')  
    m.connect()  
    result = m.get_result_queue()
      
    while True:
        if not result.empty():
            res=result.get(timeout=1)  
            print("get %d from queue" % res)
        else:
            continue



if __name__=='__main__':
    connectServer()    
