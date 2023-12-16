#!/bin/bash
# Embedded sensor module
app_name="history-service-module"
profile="prod"

curr_date=$(date +%F_%X)
log_file="/var/log/home_active_manager/startup_log"

path=$(dirname "$0")
if [[ $1 == "dev" ]];then
  profile="dev"
fi

echo "${curr_date} Start ${app_name} application with profile ${profile}" >> $log_file

java_args=("-Dspring.profiles.active=${profile}")

sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &