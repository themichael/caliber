# Caliber
Caliber is a performance management suite to collect and analyze evaluations of Revature trainees. 

#Features <br>
* Trainers can input grades and feedback for each of their trainees. 
* The quality department can input their evaluations on the batch of trainees (WIP)
* Trainers and quality personnel can comment on a trainee's profile, as well as limit the visibilty of the comments. 
* External clients and trainees can view reports on a trainee or a batch if provided a URL and token (WIP)
* The progress of trainees can be charted by technology or by week using bar charts, pie charts or line graphs.
* New batches can be added and they will persist year after year with batch data.
* Single trainees can be evaluated by technology or by week.

The Caliber suite also provides a variety of reports on different levels of granularity.

#Screenshots
<img src="https://github.com/pjw6193/caliber/blob/master/images/all%20batch%20chart.png?raw=true" height="500" width="600"/>
<br/>
#Environment 
* Requires registration as a Salesforce connected app
* Users registered in the Salesforce with matching email in Users table
* Necessary Environment variables:

<br/>JAVA_HOME=/usr/java/jdk1.8.0_111/
<br/>CALIBER_DB_URL=jdbc:oracle:thin:@something:1521:orcl
<br/>CALIBER_DB_USER=scott
<br/>CALIBER_DB_PASS=tiger
<br/>SALESFORCE_CLIENT_ID=omnomnom
<br/>SALESFORCE_CLIENT_SECRET=orz
<br/>SALESFORCE_REDIRECT_URI=https://caliber.revature.tech:443/authenticated
<br/>CALIBER_PROJECT_URL=http://caliber.revature.tech/caliber/
<br/>CALIBER_SERVER_URL=http://caliber.revature.tech/


![alt tag](https://media.glassdoor.com/sqll/1266141/revature-squarelogo-1461616605672.png "Revature")
