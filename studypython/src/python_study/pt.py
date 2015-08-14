#! /usr/bin/python
# pt.python

import os,sys,shutil
import logging
import zipfile
import glob
from datetime import datetime
from os.path import join

logging.basicConfig(level=logging.INFO)
home=os.getcwd()
jmeter_home=join(home,'apache-jmeter-2.13')
jmeterCmd=join(jmeter_home,'bin/jmeter.sh')
scriptDir=join(home,'script')
logDir=join(home,'log')
resultDir=join(home,'result')
CMDRunnerPath=join(jmeter_home,'lib/ext/CMDRunner.jar')
configFiePath=join(home,'cbioProxyServicePerf.properties')


def mkdirIfNotExist(path):
    '''
create the path if not exist otherwise backup its content
    '''
    if not os.path.exists(path):
        logging.info('####### create path '+path+' #######')
        os.makedirs(path)
    else:
        # shutil.rmtree(path)
        # os.makedirs(path)
        backup(path)
    pass


def backup(path): 
    logging.info('####### compress existing files: #######')
    file_name=datetime.now().strftime('%Y%m%d%H%M%S')+'_backup.zip'

    files = glob.glob(path+'/*')
    f = zipfile.ZipFile(join(path,file_name), 'w', zipfile.ZIP_DEFLATED)
 
    for file in files:
        if not os.path.splitext(file)[1]==".zip":
            logging.info('####### compress '+file+' #######')
            f.write(file, os.path.basename(file))

    f.close()
    logging.info('####### Delete existing files which have been zipped before. #######')
    map(lambda f: os.remove(os.path.join(path,f)),[f for f in os.listdir(path) if not os.path.splitext(f)[1]=='.zip'])
    pass

def createPngFor(currentJMXName):
    jtlFiles = os.listdir(join(resultDir,currentJMXName))

    for jtl in jtlFiles:
        if os.path.splitext(jtl)[1] == '.zip':
            continue
        elif jtl == 'runtime_tps.jtl':
            logging.info('####### generate png for '+jtl+' #######')
            os.system('java -jar '+ CMDRunnerPath+  ' --tool Reporter --generate-png '+join(resultDir,currentJMXName,os.path.splitext(jtl)[0]+'.png')+ ' --input-jtl ' + join(resultDir,currentJMXName,jtl) +' --plugin-type TransactionsPerSecond > /dev/null')
            os.system('java -jar '+ CMDRunnerPath+  ' --tool Reporter --generate-png '+join(resultDir,currentJMXName,os.path.splitext(jtl)[0]+'_latencies.png')+ ' --input-jtl ' + join(resultDir,currentJMXName,jtl) +' --plugin-type ResponseTimesOverTime > /dev/null')
        else:
            logging.info('####### generatint png for'+jtl+' #######')
            os.system('java -jar '+ CMDRunnerPath+  ' --tool Reporter --generate-png '+join(resultDir,currentJMXName,os.path.splitext(jtl)[0]+'.png')+ ' --input-jtl ' + join(resultDir,currentJMXName,jtl) +' --plugin-type PerfMon > /dev/null')
    pass
    


def runJmeter(jmx):
    os.system(jmeterCmd+' -p '+configFiePath +' -nt '+scriptDir+'/'+jmx + ' -l ' + join(logDir,currentJMXName,'log.csv'))
    pass


def executJMX():
    global currentJMXName
    scripts= os.listdir(scriptDir)
    print(len(scripts))
    for jmx in scripts:
        fileName = os.path.splitext(jmx)
        if fileName[1]=='.jmx':
            currentJMXName=fileName[0]
        else:
            continue
        logging.info('####### start to execute '+jmx+' #######')
        mkdirIfNotExist(join(logDir,currentJMXName))
        mkdirIfNotExist(join(resultDir,currentJMXName))

        runJmeter(jmx)
        logging.info('####### move the latest jtl fiels to '+join(resultDir,currentJMXName)+' #######')
        map(lambda f:shutil.move(os.path.join(resultDir,f),os.path.join(resultDir,currentJMXName)),[f for f in os.listdir(resultDir) if os.path.isfile(os.path.join(resultDir,f))])
        createPngFor(currentJMXName)
        logging.info('!------- Execute '+jmx+' completed. -------!')
        # break

        pass

if __name__ == '__main__':
    # print("\n".join(["%s=%s" %(k,v) for k,v in globals().items() if not k.startswith('__')]))
    if not os.path.exists(configFiePath):
        logging.err("configFiePath not exist,program exit.")
        sys.exit(1)
    executJMX()
    pass
