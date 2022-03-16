#!/bin/bash
# User management module
app_name="authorization-service-module"
profile="prod"

curr_date=$(date +%F_%X)
log_file="/var/log/home_active_manager/startup_log"

path=$(dirname "$0")

if [[ $1 == "dev" ]];then
  profile="dev"
  source ./${path}/dev.config
else
  source ./${path}/prod.config
fi

echo "${curr_date} Start ${app_name} application with profile ${profile}" >> $log_file

java_args=("-Dspring.profiles.active=${profile}")
java_args+=("-Dspring.datasource.url=${DATASOURCE_URL}")
java_args+=("-Dspring.datasource.username=${DATASOURCE_USERNAME}")
java_args+=("-Dspring.datasource.password=${DATASOURCE_PASSWORD}")

sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &