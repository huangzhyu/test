#coding=utf-8
'''
Created on 2015年7月31日

@author: huangzha
'''
from _operator import attrgetter





class Student: 
    def __init__(self, name, grade, age): 
                self.name = name 
                self.grade = grade 
                self.age = age  


    def __repr__(self): 
        return repr((self.name, self.grade, self.age)) 





if __name__ == '__main__':
    
    #（名字，班级，年龄）
    student_tuples = [ 
        Student('john', 'A', 15),
        Student('green', 'B', 10),
        Student('dave', 'B', 12),
    ] 
#     排序前
    print(student_tuples)
    
#     按照年龄升序
    lst=sorted(student_tuples,key=lambda student:student.age)
    print(lst)
    
    #     按照年龄升序
    lst=sorted(student_tuples,key=lambda student:student.age,reverse=True)
    print(lst)
    
#     按照年级和年龄排序
    lst=sorted(student_tuples,key=attrgetter("grade","age"))
    print(lst)
    
    
    pass
