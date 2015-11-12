#coding=utf-8
'''
Created on 2015-08-12 09:26

@author: huangzha
'''
import time  
from multiprocessing.managers import BaseManager  
  
class QueueManager(BaseManager):  
    pass  
  

    
    
def connectServer():    
    QueueManager.register('get_task_queue')  
  
    server_addr = '127.0.0.1'  
    print('Connect to server %s...' % server_addr)  
    m = QueueManager(address=(server_addr, 5000), authkey='abc')  
    m.connect()  
    task = m.get_task_queue()  
    for i in range(20):  
        task.put(i)  
        print("put %d into queue" % i)
        time.sleep(1)
    print('worker exit.')  



if __name__=='__main__':
    connectServer()    
