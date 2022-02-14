#!/bin/bash
# Embedded sensor module
app_name="application-monitoring-module"
profile="prod"

path=$(dirname "$0")
if [[ $1 == "dev" ]];then
  profile="dev"
fi

echo "Start ${app_name} application with profile ${profile}"

java_args=("-Dspring.profiles.active=${profile}")

sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &