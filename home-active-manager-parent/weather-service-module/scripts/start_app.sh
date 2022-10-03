#!/bin/bash
# Embedded sensor module
app_name="weather-service-module"

path=$(dirname "$0")
profile="prod"

curr_date=$(date +%F_%X)
log_file="/var/log/home_active_manager/startup_log"

if [[ $1 == "dev" ]];then
  profile="dev"
  source ${path}/dev.config
else
  source ${path}/prod.config
fi

echo "${curr_date} Start ${app_name} application with profile ${profile}" >> $log_file

java_args=("-Dcustom.ipInternalSensor=${IP_INTERNAL_SENSOR}")
java_args+=("-Dcustom.ipExternalSensor=${IP_EXTERNAL_SENSOR}")
java_args+=("-Dcustom.ipAirLy=${AIRLY_IP}")
java_args+=("-Dcustom.ipAirLyInstallation=${AIRLY_INSTALLATION_IP}")
java_args+=("-Dcustom.apiKeyAirLy=${AIRLY_API_KEY}")
java_args+=("-Dcustom.ipSunSetRise=${SUN_RISE_SET_IP}")
java_args+=("-Dcustom.urlAccuWeather=${ACCU_WEATHER_URL}")

sudo java -jar ${java_args[@]} ${path}/${app_name}-*.jar &