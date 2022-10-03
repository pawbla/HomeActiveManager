#!/bin/bash
# Embedded sensor module
app_name="embedded-sensor-module"
profile="prod"

curr_date=$(date +%F_%X)
log_file="/var/log/home_active_manager/startup_log"

path=$(dirname "$0")
source ${path}/app.config

if [[ $1 == "dev" ]];then
  profile="dev"
fi

echo "${curr_date} Start ${app_name} application with profile ${profile}" >> $log_file

java_args=("-Dspring.profiles.active=${profile}")
java_args+=("-Dcustom.dhtDataPin=${DHT_DATA_PIN}")
java_args+=("-Djava.library.path=${path}/")

sudo gpio mode ${DHT_SUPPLY_PIN} out
sudo gpio write ${DHT_SUPPLY_PIN} 1
sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &