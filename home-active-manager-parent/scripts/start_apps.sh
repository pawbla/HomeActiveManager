#!/bin/bash
echo "Run Home Active Manager applications"

for app_folder in $(ls -d */ | cut -f1 -d'/'); do
  echo "Selected directory ${app_folder}"
  sudo bash ./${app_folder}/start_app.sh $@
done