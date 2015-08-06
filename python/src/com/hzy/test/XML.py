#coding=utf-8
'''
Created on 2015-08-05 12:41

@author: huangzha
'''
import xml.sax
import os.path


class Handler(xml.sax.ContentHandler):
    
    def __init__(self):
        self.path=""
        self.CurrentTag=""
        self.flag=False
        
        
     
    def startElement(self, tag, attributes):
        if tag=="stringProp":
            for attr in attributes.getQNames():
                if attributes.getValueByQName(attr) == "HTTPSampler.path":
                    self.CurrentTag=tag
                    self.flag=True
    
    def endElement(self, tag):
        self.flag=False
    
    
        
    def characters(self, content):
        if self.CurrentTag=="stringProp" and self.flag:
            print("Path="+content)


if __name__ == '__main__':
    # 创建一个 XMLReader
    parser = xml.sax.make_parser()
    # turn off namepsaces
    parser.setFeature(xml.sax.handler.feature_namespaces, 0)

    # 重写 ContextHandler
    Handler = Handler()
    parser.setContentHandler( Handler )
    
    fileDir=os.path.join(os.getcwd(),"jmeter scripts")
    
    for f in os.listdir(fileDir):
        print(f)
        parser.parse(os.path.join(fileDir,f))
