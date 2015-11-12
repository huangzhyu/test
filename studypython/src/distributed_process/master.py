#coding=utf-8
'''
Created on 2015-08-12 09:26

@author: huangzha
'''

import Queue  
from multiprocessing.managers import BaseManager  
  

  
class Queuemanager(BaseManager):  
    pass  
  



def setUpServer():
    task_queue = Queue.Queue()  
    result_queue = Queue.Queue()  
    Queuemanager.register('get_task_queue', callable=lambda:task_queue)  
    Queuemanager.register('get_result_queue', callable=lambda:result_queue)  
  
    manager = Queuemanager(address=('', 5000), authkey='abc')  
  
    manager.get_server().serve_forever()  
  
#     task = manager.get_task_queue()  
#     result = manager.get_result_queue()  
#    
#     for i in range(10):  
#         n = random.randint(0, 10000)  
#         print 'put task %d ...' % n  
#         task.put(n)
#    
#         print 'try get result...'  
#         for i in range(10):  
#             print 'result is %s' % result.get(timeout=10)  
#        
    manager.shutdown()  
    print("master exit")
    
    
    
if __name__=='__main__':
    setUpServer()    
