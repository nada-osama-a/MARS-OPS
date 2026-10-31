public class CommunicationStation extends Facility {

    public CommunicationStation(String name, int id, int capacity,
            String location, boolean isOperational) {
        super(name, id, capacity, location, isOperational);
    }

    @Override
    public boolean providesCommunication() {
        return isOperational();
    }
}