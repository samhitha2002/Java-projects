import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */

	
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		// WRITE YOUR CODE HERE	

		TNode walk = new TNode(); //head
		walk.location = 0;
		TNode first = walk;
 
		for (int i = 0; i<locations.length; i++ ){
			TNode newNode = new TNode();
			newNode.next = null;
			newNode.location = locations[i];

			while (first.next != null){
				first = first.next;
			}
			first.next = newNode;
		}

		TNode bus = new TNode();
		bus.location = 0;
		TNode firstBus = bus;

		for (int i = 0; i<busStops.length; i++ ){
			TNode newNode = new TNode();
			newNode.next = null;
			newNode.location = busStops[i];

			
				while (firstBus.next != null){
					firstBus = firstBus.next;
				}
			firstBus.next = newNode;
		}
		firstBus = bus;

		TNode train = new TNode();
		train.location = 0;
		TNode firstTrain = train;

		for (int i = 0; i<trainStations.length; i++ ){
			TNode newNode = new TNode();
			newNode.next = null;
			newNode.location = trainStations[i];

				while (firstTrain.next != null){
					firstTrain = firstTrain.next;
				}
			firstTrain.next = newNode;
		}
		
		first = walk;
		firstBus = bus;
		firstTrain = train;

		while(firstBus != null){
			if (firstBus.location == first.location){
				firstBus.down = first;
				firstBus = firstBus.next;
			}
			first = first.next;
		}
		
		firstBus = bus;
		while(firstTrain != null){
			if (firstBus.location == firstTrain.location){
				firstTrain.down = firstBus;
				firstTrain = firstTrain.next;
			}
			firstBus = firstBus.next;
		}

		return train;
	}
	
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		// WRITE YOUR CODE HERE

		TNode prev = null;
		TNode ptr = trainZero;
		while (ptr != null){
			if(ptr.location == station){
				prev.next = ptr.next;
			}
			prev = ptr;
			ptr = ptr.next;
		}
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		// WRITE YOUR CODE HERE

		TNode trainFirst = trainZero;
        TNode busFirst = trainFirst.down;
        TNode walkFirst = busFirst.down;
        TNode ptr = walkFirst;

		while(walkFirst.next != null){
			walkFirst = walkFirst.next;
		}
		if(walkFirst.location < busStop){
			return;
		}
		
		walkFirst = busFirst.down;

        while(busFirst != null && walkFirst != null){
			if(busFirst.location == busStop){
				return;
			}

			if(busFirst.next == null){
				TNode addNode = new TNode();
                addNode.location = busStop;
                addNode.next = null;
                busFirst.next = addNode;
                break;
			}
            if (busFirst.location < busStop && busFirst.next.location > busStop){
                TNode addNode = new TNode();
                addNode.location = busStop;
                addNode.next = busFirst.next;
                busFirst.next = addNode;
                break;
            }
            busFirst = busFrst.next;
            walkFirst = walkFirst.next;
			
        }
		
        while(busFirst != null){
            if (busFirst.location == walkFirst.location){
                busFirst.down = walkFirst;
                busFirst = busFirst.next;
            }
            walkFirst = walkFirst.next;
			
        }
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		// WRITE YOUR CODE HERE

		ArrayList<TNode> array = new ArrayList<TNode>();
		TNode ptr = trainZero;

		while(ptr != null){
			if(ptr.location == destination){
				array.add(ptr);
				while(ptr.down != null){
					ptr = ptr.down;
					array.add(ptr);
				}
				break;
			}
			if(ptr.next == null){
				array.add(ptr);
				ptr = ptr.down;
			}
			else if(ptr.next.location > destination){
				array.add(ptr);
				ptr = ptr.down;
			}
			else if(ptr.location < destination){
				array.add(ptr);
				ptr = ptr.next;
			}
			
		}
		return array;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		// WRITE YOUR CODE HERE

		TNode train = trainZero.next;
		TNode bus = trainZero.down.next;
		TNode walk = trainZero.down.down.next;

		TNode trainFirst = new TNode();
		while (train != null){
			makeDuplicateNode(trainFirst, train.location);
			train = train.next;
		}

		TNode busFirst = new TNode();
		while (bus !=null){
			makeDuplicateNode(busFirst, bus.location);
			bus = bus.next;
		}

		TNode walkFirst = new TNode(); 
		while(walk != null){
			makeDuplicateNode(walkFirst, walk.location);
			walk = walk.next;
		}

		bus = busFirst;
		walk = walkFirst;
		while(bus != null){
			if (bus.location == walk.location){
				bus.down = walk;
				bus = bus.next;
			}
			walk = walk.next;
		}

		bus = busFirst;
		train = trainFirst;

		while(train != null){
			if (bus.location == train.location){
				train.down = bus;
				train = train.next;
			}
			bus = bus.next;
		}

		return trainFirst;
	}

	private static void makeDuplicateNode(TNode head, int locationValue){
		TNode duplicateNode = new TNode();
		duplicateNode.next = null;
		duplicateNode.location = locationValue;
		TNode first = head;
		while (first.next != null){
			first = first.next;
		}
		first.next = duplicateNode;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		// WRITE YOUR CODE HERE

		TNode train = trainZero;
		train.location = 0;
		TNode trainHead = train;
	
		TNode bus = trainZero.down;
		bus.location = 0;
		TNode busHead = bus;

		TNode walk = trainZero.down.down;
		walk.location = 0;
		TNode walkHead = walk;

		TNode scooter = new TNode(); //head
		scooter.location = 0;
		TNode scooterptr = scooter;
 
		for (int i = 0; i<scooterStops.length; i++ ){
			TNode newNode = new TNode();
			newNode.next = null;
			newNode.location = scooterStops[i];

			while (scooterptr.next != null){
				scooterptr = scooterptr.next;
			}
			scooterptr.next = newNode;
		}

		scooterptr = scooter;
		busHead = bus;
		while(busHead != null){
			if (busHead.location == scooterptr.location){
				busHead.down = scooterptr;
				busHead = busHead.next;
			}
			scooterptr = scooterptr.next;
		}
		
		walkHead = walk;
		scooterptr = scooter;
		while(scooterptr != null){
			if (walkHead.location == scooterptr.location){
				scooterptr.down = walkHead;
				scooterptr = scooterptr.next;
			}
			walkHead = walkHead.next;
		}
		
	}
}