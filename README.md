MARS-OPS: Mars Colony Operations Simulation - README

Table of Contents
---
1. System Description & Main Components
2. Design Decisions & Assumptions
3. Resource Allocation Rules
4. Mission States
5. Emergency Rules
6. Environmental Effects
7. Implementation Decisions Left Open
9. Used website for the UML

---

1. System Description & Main Components

MARS-OPS is a discrete-time simulation where a central colony coordinates agents, facilities, missions, and emergencies.

Package Layout & Key Classes

* Agent/: Workers who carry out tasks.
* CrewMembers: Humans with skills, fatigue levels (LOW, MEDIUM, HIGH), and health status (Engineer, MedicalOfficer, Scientist).
* Robot: Machines with battery levels (0-100) and damage statuses (Drone, ExplorationRover, RepairRobot).


* Missions/: Units of work with durations, resource requirements, and states (ExplorationMission, ResearchMission, RepairMission, RescueMission).
* Emergencies/: Unexpected incidents handled at the end of a time tick (OxygenLeakEmergency, RoverBreakdownEmergency, Fire, MedicalEmergency).
* Facilities/: Structures holding resources and equipment (Habitat, Laboratory, PowerStation, StorageFacility, LifeSupportSystem).
* Colony/: MarsColony is the central coordinator linking everything together.
* Environments/: External weather/conditions (DUST_STORM, COMM_DISRUPTION, NORMAL).
* Simulations/: Contains the clock (Simulation) and the executable demonstration (MainTest).

---

2. Design Decisions & Assumptions

* Polymorphism over Type Switches: Objects decide their own capabilities (canPerform, canHandleEmergency), saving the colony from messy if-else chains.
* Single Active Mission: The colony handles one active mission at a time. Submitting a new one while another is running gets rejected.
* Atomic Submissions: All validation checks happen before taking any resources or locking agents. If a check fails, nothing changes (no partial deductions).
* Discrete Time: One call to updateTime() equals one time unit (tick).
* Resources Live in Facilities: There is no global resource pool; the colony calculates totals by summing up resources across all operational facilities.

---

3. Resource Allocation Rules

* First-Fit Policy: When the colony needs a resource, it walks through the facilities in the exact order they were registered.
1. Skip non-operational facilities.
2. Take as much as needed from the current facility until the request is fulfilled.


* Facility Upkeep: Every tick, operational facilities consume 1 unit of each resource they hold (except medical supplies and spare parts).
* Emergency Rollback: If an emergency starts consuming resources (like spare parts and batteries) and runs out midway, everything consumed so far is rolled back so partial supplies aren't lost.

---

4. Mission States

Missions move through four states using MissionState:

1. ASSIGNED: Created and validated.
2. IN_PROGRESS: Running and ticking down its duration.
3. COMPLETED: Finished successfully (grants rewards like data points or fixes facilities).
4. FAILED: Rejected during validation or broken by an emergency (e.g., rover breakdown).

---

5. Emergency Rules

Emergencies queue up and are handled at the end of a time tick:

* Oxygen Leak: Requires an operational habitat, an available engineer + repair robot, and spare parts/batteries. Fixes life support.
* Rover Breakdown: Marks a rover damaged, automatically fails any mission it was on, and requires human/robot repair crews.
* Fire: Occurs in a laboratory, consuming oxygen and water, temporarily disabling the lab until extinguished.
* Medical Emergency: Requests medical supplies at a target facility to assist a crew member.

---

6. Environmental Effects

External conditions change how missions start:

* NORMAL: All missions allowed.
* DUST_STORM: Blocks ExplorationMissions (Surface operations are not allowed).
* COMM_DISRUPTION: Blocks RescueMission and ExplorationMissions.

Decision: Missions already IN_PROGRESS are not aborted if a storm starts mid-way. The environment only acts as a gatekeeper at submission time.

---

7. Implementation Decisions Left Open

* Fatigue: Stored as strings (LOW, MEDIUM, HIGH). High fatigue stops humans from working.
* Robots: Battery ranges from 0-100; robots below 20% battery cannot take missions. (They do not auto-recharge).
* Data Points: Completed research missions grant a fixed +100 data points.

---

9. Used website for the UML

* https://miro.com/app/board/uXjVEUsXXMk=/?share_link_id=580251509952

* https://mermaid.js.org/

---