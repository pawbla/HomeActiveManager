#!/bin/bash
curr_date=$(date +%F_%X)
log_file="/var/log/home_active_manager/startup_log"
echo "$curr_date Run Home Active Manager applications" >> $log_file

for app_folder in $(ls -d */ | cut -f1 -d'/'); do
  echo "$curr_date Selected directory ${app_folder}" >> $log_file
  sudo bash ./${app_folder}/start_app.sh $@ >> $log_file 2>&1
done