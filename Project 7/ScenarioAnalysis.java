/*************************************************************************
 *  Compilation:  javac ScenarioAnalysis.java
 *  Execution:    java ScenarioAnalysis
 *
 *  @author: Samhitha Padmanabhini, sp1949@scarletmail.rutgers.edu, sp1949
 *
 *************************************************************************/
public class ScenarioAnalysis {

    // Instance variables
    private Vehicle[] vehicles;       // all vehicless being analyzed 
    private double  gasPrice;         // price of one gallon of gas in dollars
    private double  electricityPrice; // price of 1 kWh in cents of a dollar, c$/kWh

    /*
     * Constructor
     */ 
    public ScenarioAnalysis ( double gasPrice, double electricityPrice ) {
        this.gasPrice = gasPrice;
        this.electricityPrice = electricityPrice;
    }

    /*
     * Updates the price of gas
     * Call computeCO2EmissionsAndCost() whenever there is an update on gas prices
     */
    public void setGasPrice ( double gasPrice ) {
        this.gasPrice = gasPrice;
        computeCO2EmissionsAndCost();
    }

    /*
     * Returns the gas price
     */ 
    public double getGasPrice () {
        return gasPrice;
    }

    /*
     * Updates the price of electricity
     * Call computeCO2EmissionsAndCost() whenever there is an update on electricity prices
     */
    public void setElectricityPrice ( double electricityPrice ) {
        this.electricityPrice = electricityPrice;
        computeCO2EmissionsAndCost();
    }
    
    /*
     * Returns electricity price
     */
    public double getElectricityPrice () {
        return electricityPrice;
    }

    /*
     * Computes and updates the CO2 emissions, fuel cost and total cost for each 
     * vehicle in the vehicles array.
     */
    public void computeCO2EmissionsAndCost () {

        double months = 12;
        double emissions = 0;
        double fuelCost = 0;

        for (int i = 0; i < vehicles.length; i++){
            double leaseLength = (vehicles[i].getLease().getNumberOfMonths()) / months; 
            double availableMiles = (vehicles[i].getLease().getMileageAllowance()) * leaseLength;
            double milesUsed = (vehicles[i].getFuel().getUsage());

            if (vehicles[i].getFuel().getType() == 1 ){
                emissions = (availableMiles / milesUsed) * 8.887;
                fuelCost = (availableMiles/milesUsed) * getGasPrice();
                vehicles[i].setFuelCost(fuelCost);
                vehicles[i].setCO2Emission(emissions);
            }else{
                if(vehicles[i].getFuel().getType() == 2){
                    double kWh = vehicles[i].getFuel().getKWhPerCharge();
                    emissions = ((availableMiles / milesUsed)* kWh) * ((998.4/1000) * 0.45);
                    fuelCost = (getElectricityPrice() * (availableMiles/milesUsed) * kWh) /100; 
                    vehicles[i].setFuelCost(fuelCost);
                    vehicles[i].setCO2Emission(emissions);
                }
            }

            double leaseMonthCost = vehicles[i].getLease().getMonthlyCost();
            double leaseLengthM = vehicles[i].getLease().getNumberOfMonths();
            double costToSign = (vehicles[i].getLease().getDueAtSigning());
            double CostOfLease = vehicles[i].getFuelCost() +  costToSign + (leaseMonthCost * leaseLengthM) + vehicles[i].getOtherCost();
            vehicles[i].setTotalCost(CostOfLease);
        }
    }

    /*
     * Returns vehicles array
     */
    public Vehicle[] getVehicles () {
        return vehicles;
    }

    /*
     * Prints all vehicles
     */
    public void printVehicles () {
        for ( Vehicle v : vehicles ) {
            StdOut.println(v);
        }
    }

    /*
     * Populates the array vehicles from file vehicles.txt
     * 
     * File Format: The file can have either gas or electric lines
     * 
     * gas,      name, cash due at signing lease,lease length in months, monthly lease cost, mileage allowance per 12 months, miles per gallon, cost of oil change
     * electric, name, cash due at signing lease,lease length in months, monthly lease cost, mileage allowance per 12 months, miles per kWh/charge, kWh per charge, cost of home charger
     */ 
    public void populateVehicleArray () {
        StdIn.setFile("vehicles.txt");

        // read the number of car models and allocate array
        int numberOfCars = StdIn.readInt();
        vehicles = new Vehicle[numberOfCars];

        for (int i = 0; i < numberOfCars; i++) {
            String fuelType = StdIn.readString();
            String name     = StdIn.readString();

            // Lease information
            double dueAtSigning  = StdIn.readDouble();
            int numberOfMonths = StdIn.readInt();
            double montlyCost  = StdIn.readDouble();
            int mileageAllowance = StdIn.readInt();
            Lease lease = new Lease(dueAtSigning,numberOfMonths,montlyCost,mileageAllowance);

            // Fuel
            double usage = StdIn.readDouble();
            Fuel fuel = null; 
            if ( fuelType.toLowerCase().equals("electric")) {
                double kWhPerCharge = StdIn.readDouble();
                fuel = new Fuel (usage, kWhPerCharge);
            } else {
                fuel = new Fuel (usage);
            }

            // other cost include oil change for gas-powered or home charger for eletric-powered
            double otherCost = StdIn.readDouble();

            // Instatiate the new vehicle
            vehicles[i] = new Vehicle (name, fuel, lease, otherCost);
        }
    }

    /*
     * Test client
     */
    public static void main (String[] args) {
        ScenarioAnalysis sa = new ScenarioAnalysis(3.45, 0.3);
        sa.populateVehicleArray();
        sa.setGasPrice(2.23);           // $2.23 per gallon of gas
        sa.setElectricityPrice(16.14);  // c$16.14 per kWh
        sa.computeCO2EmissionsAndCost();
        sa.printVehicles();
    }
}