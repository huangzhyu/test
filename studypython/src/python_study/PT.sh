#! /bin/bash
# PT.sh
# Used to execute jmeter script and generate png with jtl
# author:xzhaohu
# Pre-condition:Following directories have to be exist before excuting.
# /home_local/drutt/PT/apache-jmeter-2.13
# /home_local/drutt/PT/script : Used to store jmx script
#

home=/home_local/drutt/PT
jmeter_home=${home}/apache-jmeter-2.13
jmeterCmd=${jmeter_home}/bin/jmeter.sh
scriptDir=${home}/script
logDir=${home}/log
resultDir=${home}/result
CMDRunnerPath=${jmeter_home}/lib/ext
config=${home}/cbioProxyServicePerf.properties


. ${config}
function runJmeter(){
 echo -e "\033[32;40mexecuting $1\033[0m"
 if [ ! -e ${config} ]; then
    exit 1
 fi
 ${jmeterCmd} -p ${config} -n -t ${scriptDir}/$1 -l ${logDir}/${currentJMX}/log.csv
}


function mkdirIfNotExist(){
    if [ ! -d $1 ]; then
      echo -e "\033[32;40mcreate directory $1\033[0m"
     mkdir -p $1
    else
       echo -e "\033[32;40mbackup existing files in $1\033[0m"
       cd $1
       tar -zcvf `date +%Y%m%d%H%M%S`_backup.tar.gz * --exclude='*backup*'  --remove-files
       cd ${home}
      #rm -rf $1/*
    fi
}

function createPngFor(){
    jtlFiles=`ls ${resultDir}/$1 --ignore=*.gz`
    jtlArray=($jtlFiles)

    if `command -v java > /dev/null 2>&1`; then
        for file in ${jtlArray[@]}; do
            echo -e "\033[32;40mcreateing PNG file for ${file}\033[0m"
            case $file in
                ${runtime_tps_report} )
                    java -jar $CMDRunnerPath/CMDRunner.jar  --tool Reporter --generate-png ${resultDir}/$1/${file%.*}_TPS.png --input-jtl  ${resultDir}/$1/$file  --plugin-type TransactionsPerSecond > /dev/null   
                    java -jar $CMDRunnerPath/CMDRunner.jar  --tool Reporter --generate-png ${resultDir}/$1/${file%.*}_latencies.png --input-jtl  ${resultDir}/$1/$file  --plugin-type ResponseTimesOverTime > /dev/null
                    ;;
                    * )
                    java -jar $CMDRunnerPath/CMDRunner.jar  --tool Reporter --generate-png ${resultDir}/$1/${file%.*}.png --input-jtl  ${resultDir}/$1/${file}  --plugin-type PerfMon > /dev/null
                    ;;
            esac
        done
    fi    



    echo -e "\033[32;40mcompress all of the jtl files and remove source file\033[0m"
}


function executJMX(){
    scripts=`ls -l ${scriptDir} | awk 'NR>1 {print $9}'`
    scriptArray=($scripts)
    for s in ${scriptArray[@]}
        do
            currentJMX=${s%.*}

            mkdirIfNotExist ${logDir}/${currentJMX}

            mkdirIfNotExist ${resultDir}/${currentJMX}
   
            runJmeter ${s}

            echo -e "\033[32;40mmoving the jtl fiels to ${resultDir}/${currentJMX}\033[0m"

            find ${resultDir}/ -maxdepth 1 -type f | xargs -I {} mv {}  ${resultDir}/${currentJMX}/
            createPngFor ${currentJMX}
        done
}



if [ ! -x $jmeterCmd ]; then
    echo -e "\033[32;40madd x previlege for jmeter.sh\033[0m"
    chmod a+x $jmeterCmd
fi

if [ ! -x $CMDRunnerPath/CMDRunner.jar ]; then
    echo -e "\033[32;40madd x previlege for CMDRunner.jar\033[0m"
    chmod a+x 
fi

currentJMX=""
executJMX