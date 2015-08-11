#coding=utf-8
'''
Created on 2015-8-5

@author: huangzha
'''

def fibonaci(initial):
    if initial<=1:
        return initial
    return fibonaci(initial-1)+fibonaci(initial-2)
    

if __name__ == '__main__':
    params = {"server":"mpilgrim", "database":"master", "uid":"sa", "pwd":"secret"}
    print(params)
    print("server is %(server)s"% params)
    
    '''
    如果没有异常会输出123和456.否则输出789
    '''
    try:
#         print("123")
        raise EOFError
    except Exception:
        print("789")
    else:
        print("456")
        
    a="a"
    b="a" 
     
    print(a==b)
    print(a is b)  
    
    container=[]
    for i in range(10):
        container.append(fibonaci(i))
        
    print(len(container))
    print(container)
    