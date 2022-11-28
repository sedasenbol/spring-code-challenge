# Spring Code Challenge
We have two separate projects below. Just provide a solution for one of them.

Development Requirements
- Application will be built with **Spring Boot**.
- Java 8 or later versions must be used
- Device data set is in `devices.json`. The data must be initially read from this source and stored in database. (Embedded database H2 is fine)
- Publish your code in a git repository (Github, Bitbucket, etc)
- Couple of commits are expected during implementation of the project


## Challenge 1: Adding Mobile Device Data to Database
---
We need a REST endpoint for storing Mobile Device data.

A device has following attributes


| field     | type   | constraints                                                          |
|-----------|--------|----------------------------------------------------------------------|
| brand     | String | cannot be null, cannot be empty                                      |
| model     | String | cannot be null, cannot be empty                                      |
| os        | String | cannot be null, cannot be empty, and it can be either **Android** or **ios** |
| osVersion | String | cannot be null, cannot be empty                                      |

And we cannot have multiple entries with same **brand**, **model** and **osVersion**

### Logical Requirements
- When the device data is posted to backend, it must be validated before saved to database.
- If the posted data is valid, it must be saved to database and it's database id must return in response.


## Challenge 2: Filtered Device List
---
We need a REST endpoint for getting the Mobile Devices data.

A device has following attributes


| field     | type    | sample value             |
|-----------|---------|--------------------------|
| brand     | String  | Samsung, Apple,...       |
| model     | String  | iPhone 7, Galaxy S8+,... |
| os        | String  | Android, iOS             |
| osVersion | String  | 9, 12.2, ...             |

Rest endpoint is expected to return the matching devices data based on the filtered parameters.

### Logical Requirements
- If no filter parameter provided, all devices data must return with Page response
- If filter parameter(s) provided only matching devices data must return in response
  - Single filter with single parameter can be provided. e.g. brand is **Samsung**
  - Multiple filters with single parameter in each can be provided. Logical operation between different filters must be **and**. e.g. brand is **Apple** *and* osVersion is **12.2**
  - response must be containing the devices data matching to the requested page.
  
  an example request will be as follows
  `/devices?brand=apple&osVersion=9&page=3`


