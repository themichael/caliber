# Caliber
Caliber is a performance management suite to collect and analyze evaluations of Revature trainees. 

#Features <br>
* Trainers can input grades and feedback for each of their trainees. 
* The quality department can input their evaluations on the batch of trainees
* Trainers and quality personnel can comment on trainee and batch performance
* Users can generate reports and charts to visualize KPIs and print PDF reports
* New batches can be added and they will persist year after year with batch data.

* The Caliber suite also provides a variety of reports on different levels of granularity:
    Week-by-week, Batch overall, Trainee weekly, Trainee overall

#Environment 
* Requires Java 8 or higher
* Validated on Apache Tomcat 7.0
* Requires Oracle EE 12c database
* Requires registration as a Salesforce connected app
* Users registered in the Salesforce with matching email in Users table
* Necessary Environment variables:

<br/>JAVA_HOME=/usr/java/jdk1.8.0_112/
<br/>CALIBER_DB_URL=jdbc:oracle:thin:@something:1521:orcl
<br/>CALIBER_DB_USER=scott
<br/>CALIBER_DB_PASS=tiger
<br/>SALESFORCE_CLIENT_ID=omnomnom
<br/>SALESFORCE_CLIENT_SECRET=orz
<br/>SALESFORCE_REDIRECT_URI=https://caliber.revature.tech:443/authenticated
<br/>CALIBER_PROJECT_URL=http://caliber.revature.tech/caliber/
<br/>CALIBER_SERVER_URL=http://caliber.revature.tech/

#ERD (3/31/2017)  
<br/>
<img src="https://github.com/pjw6193/caliber/blob/master/images/caliber-local.png?raw=true" height="200" width="300"/>
<br/>

#Local deployment
* To deploy locally, you must disable Salesfore authentication since you need a valid callback URL.
* Simply switch to the dummy request mapping methods in the following classes:
	com.revature.caliber.controllers.BootController 
	com.revature.caliber.security.impl.AuthorizationImpl

#Screenshots
All Batch Performance (vp)
<br/>
<img src="https://github.com/pjw6193/caliber/blob/master/images/all-current-batch.png" height="200" width="300"/>
<br/>
Batch Performance (vp)
<br/>
<img src="https://github.com/pjw6193/caliber/blob/master/images/batch-performance.png?raw=true" height="200" width="300"/>
<br/>
Individual Trainee Performance (all)
<br/>
<img src="https://github.com/pjw6193/caliber/blob/master/images/trainee-performance.png?raw=true" height="200" width="300"/>
<br/>
Week Progress (all)
<br/>
<img src="https://github.com/pjw6193/caliber/blob/master/images/vp-week-progress.png?raw=true" height="200" width="300"/> 
<br/>

![alt tag](https://media.glassdoor.com/sqll/1266141/revature-squarelogo-1461616605672.png "Revature")
