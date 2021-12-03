#!/bin/bash
# Embedded sensor module
app_name="embedded-sensor-module"
profile="prod"

path=$(dirname "$0")
source ./${path}/app.config

if [[ $1 == "dev" ]];then
  profile="dev"
fi

echo "Start ${app_name} application with profile ${profile}"

java_args=("-Dspring.profiles.active=${profile}")
java_args+=("-Dcustom.dhtDataPin=${DHT_DATA_PIN}")
java_args+=("-Dcustom.dhtSupplyPin=${DHT_SUPPLY_PIN}")

sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &