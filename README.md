# Illumio_Coding_Challenge
Implementation for the coding challenge

## TESTING
I have used junit5 for testing. My main purpose of this unit testing was to check if a valid and invalid case would work. I also checked for the scenario that a file doesn’t exist. Even if a file is not found  since exception handling is present it shouldn’t cause a disruption in the Junit testing.

## IMPLEMENTATION
I have used three classes for functional segregation.
1.Firewall Properties class is simple model class with direction, stream, port and IP as attributes.
In this class I have overridden the equals method so that we can have   firewall properties object equal only if the stream, port, ip and direction are equal.
2.Firewall class.
It has a constructor for setting the file path .There are two main methods in this class one is for accepting a packet and checking if the firewall rule exists in the hash set and the other is for setting the FilePath .The other methods are for splitting ip’s and ports based on ranges in the input file.
Overriding the equals method makes sure that the accept_packet function has a time complexity of O(1).

## REFINEMENTS
If I had more time I would like to inculcate design patterns for efficiency.Also would like to come up with a better way for ip and port ranges split.
## TEAMS
If given a chance I would love to work for the policy team or Data Team.
