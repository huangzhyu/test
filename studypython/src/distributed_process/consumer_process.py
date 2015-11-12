#coding=utf-8
'''
Created on 2015-08-12 09:26

@author: huangzha
'''

from multiprocessing.managers import BaseManager  
  
class QueueManager(BaseManager):  
    pass  
  

    
    
def connectServer():    
    QueueManager.register('get_task_queue')  
    QueueManager.register('get_result_queue')
    server_addr = '127.0.0.1'  
    print('Connect to server %s...' % server_addr)  
    m = QueueManager(address=(server_addr, 5000), authkey='abc')  
    m.connect()  
    task = m.get_task_queue()  
    result=m.get_result_queue()

    while True:
        if not task.empty():
            n = task.get(timeout=1)  
            print('run task %d * %d...' % (n, n))  
            result.put(n * n)
        else:
            continue

if __name__=='__main__':
    connectServer()    
