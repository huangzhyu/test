#coding=utf-8
'''
Created on 2015/8/3

@author: huangzha
'''

import os

def stripnulls(tag,start,end):
    print(tag+","+str(start)+","+str(end))
    pass

def complicated():
    tagDataMap = {"title" : (3, 33, stripnulls),
                  "artist" : (33, 63, stripnulls),
                  "album" : (63, 93, stripnulls),
                  "year" : (93, 97, stripnulls),
                  "comment" : (97, 126, stripnulls),
                  "genre" : (127, 128, stripnulls)}
    
    
    for tag,(start,end,func) in tagDataMap.items():
        func(tag,start,end)


def readFile(path):
    f = open(path, mode='r')
    print(f.name)
    print(f.mode)
#     print(file.__doc__)
    print("The content of the file is: \n"+f.read())
    print("The length of the file is :"+str(f.tell()))
    file.close()
    print("The file is :"+str(1 and f.closed and "Closed" or "Open"))
    

def writeFile(path):
    logfile=open(path,'w')
    logfile.write('hello world')
    logfile.close()
    
def appendFile(path):
    logfile=open(path,'a')
    logfile.write("\nappend content")
    logfile.close()
    
    
    
def listDirectory(directory, fileExtList): 
    "get list of file info objects for files of particular extensions"
    fileList = [os.path.normcase(f)
    for f in os.listdir(directory)] 
    fileList = [os.path.join(directory, f)
    for f in fileList if os.path.splitext(f)[1] in fileExtList]
    


if __name__=='__main__':
    path='./logFile.txt'
    writeFile(path)
    readFile(path)
    appendFile(path)
    readFile(path)
    complicated()
    pass
