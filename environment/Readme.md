## Environemt

### Assumptions
Static content (React application) is shared using properly configured Nginx server.

### Configuration

Configuration is stored in ```nginx.conf``` file and should be stored on RaspberryPi in ```/etc/nginx/nginx.conf``` 

### Usefull commands:

 - Install Nginx ```sudo apt-get install nginx```
 - Start server ```sudo /etc/init.d/nginx start```
 - Restart server ```sudo systemctl restart nginx```
 - logs are stored under ```/var/log/nginx/error.log```
