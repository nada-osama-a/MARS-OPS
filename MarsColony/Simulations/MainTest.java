package MarsColony.Simulations;

import MarsColony.Colony.MarsColony;
import MarsColony.Agent.Agents;
import MarsColony.Agent.CrewMembers.*;
import MarsColony.Agent.Robot.*;
import MarsColony.Emergencies.*;
import MarsColony.Environments.*;
import MarsColony.Facilities.*;
import MarsColony.Missions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Executable demonstration of the five required Mars colony scenarios.
 * Run with: javac MarsColony\\**\\*.java && java MarsColony.Simulations.MainTest
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  MARS COLONY OPERATIONS — SCENARIO TEST");
        System.out.println("==============================================\n");

        runScenario1SuccessfulExploration();
        runScenario2MissionRejection();
        runScenario3ResourceShortage();
        runScenario4EmergencyHandling();
        runScenario5EnvironmentalEffect();

        System.out.println("==============================================");
        System.out.println("  ALL FIVE SCENARIOS COMPLETE");
        System.out.println("==============================================");
    }

    // ------------------------------------------------------------------
    // Scenario 1: Successful Exploration
    // ------------------------------------------------------------------
    private static void runScenario1SuccessfulExploration() {
        header("SCENARIO 1: Successful Exploration");

        MarsColony colony = new MarsColony();

        Scientist scientist = new Scientist(
                "Dr. Amira Hassan", "SCI-01", 80, "LOW", true, "Base Camp", true, null);
        ExplorationRover rover = new ExplorationRover(
                "Pathfinder", "ROV-01", 95, false, "Base Camp", true, null);

        StorageFacility depot = new StorageFacility(
                "Surface Depot", 1, 200, "Base Camp", true);
        depot.addResource(new Resource(ResourceType.FUEL, 40));
        depot.addResource(new Resource(ResourceType.OXYGEN, 30));
        depot.addEquipment(new Equipment(EquipmentType.EXPLORATION));

        colony.getColonyAgents().add(scientist);
        colony.getColonyAgents().add(rover);
        colony.getColonyFacilities().add(depot);

        List<Resource> missionResources = List.of(new Resource(ResourceType.FUEL, 10));
        ExplorationMission mission = new ExplorationMission(
                1, missionResources, List.of(scientist, rover), "Valles Marineris", 50);

        System.out.println("Created scientist: " + scientist.getName()
                + " (skill=" + scientist.getSkill() + ", available=" + scientist.isAvailable() + ")");
        System.out.println("Created exploration rover: " + rover.getName()
                + " (battery=" + rover.getBatteryLevel() + ", available=" + rover.isAvailable() + ")");
        System.out.println("Created exploration equipment at " + depot.getName());
        System.out.println("Created exploration mission to " + mission.getTargetLocation()
                + " (duration=" + mission.getDuration() + " time unit)");

        System.out.println("\n--- SYSTEM STATE BEFORE EXECUTION ---");
        printMissionAndAgents(mission, scientist, rover, colony);
        System.out.println("Fuel in depot: " + depot.getResourceQuantity(ResourceType.FUEL));
        System.out.println("Active colony mission: " + colony.getActiveMission());

        List<Agents> team = new ArrayList<>();
        team.add(scientist);
        team.add(rover);
        boolean submitted = colony.submitMission(mission, team);

        // Duration is 1: one time tick completes the mission and releases agents.
        colony.updateTime();

        System.out.println("\n--- SYSTEM STATE AFTER EXECUTION ---");
        System.out.println("Submission accepted: " + submitted);
        printMissionAndAgents(mission, scientist, rover, colony);
        System.out.println("Fuel in depot: " + depot.getResourceQuantity(ResourceType.FUEL)
                + " (10 consumed by the mission; 1 consumed by facility upkeep)");
        System.out.println("Active colony mission: " + colony.getActiveMission());
        System.out.println("Result: Exploration completed successfully.\n");
    }

    // ------------------------------------------------------------------
    // Scenario 2: Mission Rejection
    // ------------------------------------------------------------------
    private static void runScenario2MissionRejection() {
        header("SCENARIO 2: Mission Rejection");

        MarsColony colony = new MarsColony();

        // An engineer can repair, but cannot explore.
        Engineer engineer = new Engineer(
                "Nadia El-Masry", "ENG-01", 90, "LOW", true, "Workshop", true, null, 100);
        StorageFacility depot = new StorageFacility(
                "Surface Depot", 2, 200, "Base Camp", true);
        depot.addResource(new Resource(ResourceType.FUEL, 40));
        depot.addEquipment(new Equipment(EquipmentType.EXPLORATION));

        colony.getColonyAgents().add(engineer);
        colony.getColonyFacilities().add(depot);

        ExplorationMission mission = new ExplorationMission(
                2,
                List.of(new Resource(ResourceType.FUEL, 5)),
                List.of(engineer),
                "Olympus Mons",
                40);

        System.out.println("Submitting an exploration mission with an unsuitable agent: "
                + engineer.getName() + " (Engineer).");
        System.out.println("Engineer canPerform(ExplorationMission) = "
                + engineer.canPerform(mission));

        boolean submitted = colony.submitMission(mission, List.of(engineer));

        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission state: " + mission.getState());
        System.out.println("Colony active mission: " + colony.getActiveMission());
        System.out.println("Agent still available: " + engineer.isAvailable());
        System.out.println("Fuel unchanged: " + depot.getResourceQuantity(ResourceType.FUEL));
        System.out.println("Result: Mission never became active.\n");
    }

    // ------------------------------------------------------------------
    // Scenario 3: Resource Shortage
    // ------------------------------------------------------------------
    private static void runScenario3ResourceShortage() {
        header("SCENARIO 3: Resource Shortage");

        MarsColony colony = new MarsColony();

        Scientist scientist = new Scientist(
                "Dr. Karim Saleh", "SCI-02", 70, "LOW", true, "Base Camp", true, null);
        ExplorationRover rover = new ExplorationRover(
                "Curiosity-2", "ROV-02", 80, false, "Base Camp", true, null);
        StorageFacility depot = new StorageFacility(
                "Fuel Cache", 3, 50, "Base Camp", true);

        int fuelBefore = 4;
        depot.addResource(new Resource(ResourceType.FUEL, fuelBefore));
        depot.addEquipment(new Equipment(EquipmentType.EXPLORATION));

        colony.getColonyAgents().add(scientist);
        colony.getColonyAgents().add(rover);
        colony.getColonyFacilities().add(depot);

        int fuelRequired = 25;
        ExplorationMission mission = new ExplorationMission(
                2,
                List.of(new Resource(ResourceType.FUEL, fuelRequired)),
                List.of(scientist, rover),
                "Jezero Crater",
                40);

        System.out.println("Mission requires " + fuelRequired + " FUEL.");
        System.out.println("Colony has " + fuelBefore + " FUEL.");

        boolean submitted = colony.submitMission(mission, List.of(scientist, rover));

        int fuelAfter = depot.getResourceQuantity(ResourceType.FUEL);
        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission state: " + mission.getState());
        System.out.println("Colony active mission: " + colony.getActiveMission());
        System.out.println("Fuel before: " + fuelBefore);
        System.out.println("Fuel after:  " + fuelAfter);
        System.out.println("Quantity went negative: " + (fuelAfter < 0));
        System.out.println("Resources consumed: " + (fuelAfter != fuelBefore));
        System.out.println("Result: Operation rejected without consuming resources.\n");
    }

    // ------------------------------------------------------------------
    // Scenario 4: Emergency Handling (oxygen leak)
    // ------------------------------------------------------------------
    private static void runScenario4EmergencyHandling() {
        header("SCENARIO 4: Emergency Handling — Oxygen Leak");

        MarsColony colony = new MarsColony();

        Habitat habitat = new Habitat("Habitat Alpha", 10, 8, "North Ridge", true);
        habitat.addResource(new Resource(ResourceType.OXYGEN, 40));
        habitat.addResource(new Resource(ResourceType.SPARE_PARTS, 6));
        habitat.addResource(new Resource(ResourceType.BATTERY, 12));

        Engineer engineer = new Engineer(
                "Youssef Nabil", "ENG-02", 85, "LOW", true, "Habitat Alpha", true, null, 100);
        RepairRobot repairRobot = new RepairRobot(
                "Wrench", "BOT-01", 90, false, "Habitat Alpha", true, null);

        colony.getColonyFacilities().add(habitat);
        colony.getColonyAgents().add(engineer);
        colony.getColonyAgents().add(repairRobot);

        int oxygenLoss = 8;
        OxygenLeakEmergency leak = new OxygenLeakEmergency(habitat, oxygenLoss);
        colony.addEmergency(leak);

        System.out.println("1. Detect the emergency");
        System.out.println("   Emergency class: " + leak.getClass().getSimpleName());
        System.out.println("   Emergency state: " + leak.getState());

        System.out.println("2. Locate the affected facility");
        System.out.println("   Facility: " + habitat.getName() + " at " + habitat.getLocation());
        System.out.println("   Leak state before response: " + habitat.getLeakState());
        System.out.println("   Oxygen before: " + habitat.getResourceQuantity(ResourceType.OXYGEN));
        System.out.println("   Spare parts before: " + habitat.getResourceQuantity(ResourceType.SPARE_PARTS));
        System.out.println("   Battery before: " + habitat.getResourceQuantity(ResourceType.BATTERY));
        System.out.println("   Life support operational: " + habitat.isLifeSupportOperational());

        System.out.println("3. Identify suitable responders");
        System.out.println("   Human candidate: " + engineer.getName()
                + " canHandle=" + engineer.canHandleEmergency(leak));
        System.out.println("   Robot candidate: " + repairRobot.getName()
                + " canHandle=" + repairRobot.canHandleEmergency(leak));

        System.out.println("4. Perform the response if possible");
        // Colony detects pending emergencies during the time update and handles them.
        colony.updateTime();

        System.out.println("5. Consume the required resources (2 spare parts, 5 battery)");
        System.out.println("   Spare parts after: " + habitat.getResourceQuantity(ResourceType.SPARE_PARTS));
        System.out.println("   Battery after: " + habitat.getResourceQuantity(ResourceType.BATTERY)
                + " (emergency used 5; facility upkeep also consumes 1 battery per tick)");

        System.out.println("6. Update the facility state");
        System.out.println("   Leak state after: " + habitat.getLeakState());
        System.out.println("   Oxygen after: " + habitat.getResourceQuantity(ResourceType.OXYGEN)
                + " (leak consumed " + oxygenLoss + "; facility upkeep also consumes 1 oxygen per tick)");
        System.out.println("   Life support operational: " + habitat.isLifeSupportOperational());
        System.out.println("   Life support efficiency: " + habitat.getLifeSupportEfficiency() + "%");
        System.out.println("   Emergency state: " + leak.getState());
        System.out.println("   Human responder: "
                + (leak.getHumanResponder() != null ? leak.getHumanResponder().getName() : "none"));
        System.out.println("   Robot responder: "
                + (leak.getRobotResponder() != null ? leak.getRobotResponder().getName() : "none"));
        System.out.println("   Engineer available after response: " + engineer.isAvailable());
        System.out.println("   Repair robot available after response: " + repairRobot.isAvailable());

        boolean success = leak.getState() == EmergencyState.RESOLVED
                && habitat.getLeakState() == LeakState.RESOLVED
                && habitat.isLifeSupportOperational();
        System.out.println("Result: " + (success ? "Oxygen leak resolved and habitat restored."
                : "Emergency handling did not fully restore the habitat.") + "\n");
    }

    // ------------------------------------------------------------------
    // Scenario 5: Environmental Effect
    // ------------------------------------------------------------------
    private static void runScenario5EnvironmentalEffect() {
        header("SCENARIO 5: Environmental Effect — Dust Storm");

        MarsColony colony = new MarsColony();
        colony.setEnvironment(new Environment(EnvironmentCondition.DUST_STORM, 6));

        Scientist scientist = new Scientist(
                "Dr. Lina Farid", "SCI-03", 75, "LOW", true, "Base Camp", true, null);
        ExplorationRover rover = new ExplorationRover(
                "Opportunity", "ROV-03", 88, false, "Base Camp", true, null);
        StorageFacility depot = new StorageFacility(
                "Surface Depot", 4, 200, "Base Camp", true);
        depot.addResource(new Resource(ResourceType.FUEL, 40));
        depot.addEquipment(new Equipment(EquipmentType.EXPLORATION));

        colony.getColonyAgents().add(scientist);
        colony.getColonyAgents().add(rover);
        colony.getColonyFacilities().add(depot);

        ExplorationMission mission = new ExplorationMission(
                2,
                List.of(new Resource(ResourceType.FUEL, 8)),
                List.of(scientist, rover),
                "Gale Crater",
                40);

        System.out.println("Environment: " + colony.getEnvironment().getType());
        System.out.println("Attempting to start an exploration mission during the storm...");

        boolean submitted = colony.submitMission(mission, List.of(scientist, rover));

        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission state: " + mission.getState());
        System.out.println("Colony active mission: " + colony.getActiveMission());
        System.out.println("Agents remain available: scientist=" + scientist.isAvailable()
                + ", rover=" + rover.isAvailable());
        System.out.println("Fuel unchanged: " + depot.getResourceQuantity(ResourceType.FUEL));
        System.out.println("Result: Dust storm blocked surface exploration.\n");
    }

    private static void header(String title) {
        System.out.println("--------------------------------------------------------------");
        System.out.println(title);
        System.out.println("--------------------------------------------------------------");
    }

    private static void printMissionAndAgents(ExplorationMission mission,
            Scientist scientist, ExplorationRover rover, MarsColony colony) {
        System.out.println("Mission state: " + mission.getState()
                + " | elapsed=" + mission.getElapsedTime()
                + "/" + mission.getDuration());
        System.out.println("Scientist available: " + scientist.isAvailable()
                + " | fatigue=" + scientist.getFatigueLevelHuman());
        System.out.println("Rover available: " + rover.isAvailable()
                + " | battery=" + rover.getBatteryLevel());
        System.out.println("Active mission on colony: "
                + (colony.getActiveMission() == null ? "none"
                        : colony.getActiveMission().getState()));
    }
}
