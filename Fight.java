import java.util.Scanner;
/**
 * The fight class takes in the players current health, equipped weapon and armor, It then creates a new enemy for the user to fight, and battles in rounds until the player or enemy has won. 
 * The fight method returns true if the player won, and false if the enemy won. If the fight method returns true, another method is called to return the players updated health.
 * @author Tyler Viacara, Toby Lane, and Chris Papp
 */
public class Fight{
	Scanner s = new Scanner(System.in);
	private int playerHealth;
	private Item equippedWeapon;
	private Item equippedArmor;

	/**Constructor that takes in the players current health, along with their current equipped weapon and armor
	 * @param playerHealth The players current health as an integer
	 * @param equippedWeapon The weapon item the player currently has attached at the time of the fight
	 * @param equippedArmor The armor item the player currently has attached at the time of the fight
	 */
	public Fight(int playerHealth, Item equippedWeapon, Item equippedArmor){
		this.playerHealth = playerHealth;
		this.equippedWeapon = equippedWeapon;
		this.equippedArmor = equippedArmor;
	}

	/**Method that is called if the fight method returns true, and returns the players updated health 
	 *@return playerHealth the new health of the player after the fight
	 */
	public int getHealth(){
		return this.playerHealth;
	}

	/**Method that is called if the player comes across an enemy. The user can attack, defend, or charge their attack
	 * The fight happens in rounds until either the player or enemy health is below 0
	 * @return true if the player won, false if the enemy won
	 */
	public boolean fight(){
		Enemy dungeonEnemy = EnemyGenerator.generate();
		boolean playerAlive = true;
		boolean enemyAlive = true;
		boolean didUserWin = false;
		boolean validInput = false;
		boolean playerCharging = false;
		int roundNumber = 1;
		int playerStrength = equippedWeapon.getStrength();
		int playerDef = equippedArmor.getStrength();
		System.out.println("A battle is about to begin!");
		System.out.println();
		//running through the fight until the player or enemy dies
		while ((playerAlive == true) && (enemyAlive == true)){
			validInput = false;
			System.out.println("Round " + roundNumber + " is about to begin!");
			System.out.println();
			char choice = ' ';
			while(validInput == false){
				//player attacking the enemy
				System.out.println("The " + dungeonEnemy.getName() + " currently has a health of " + dungeonEnemy.getHealth());

				try{
					Thread.sleep(2000);
				}
				catch (InterruptedException z){
					System.out.println("Interrupted!");
				}
				System.out.println("Press H to Attack");
				System.out.println("Press J to Defend, using a turn, but increasing your defense for the Enemies next attack. You then counterattack for half what they hit you for.");
				System.out.println("Press K to Charge, wasting a turn, but increasing your attack for the next turn.");
				System.out.println("What would you like to do?");
				try{
					String choices = s.nextLine();
					choice = choices.charAt(0);
					System.out.println();
				}
				catch(StringIndexOutOfBoundsException nothingEntered){
					validInput = false;
				}
				try{

					Thread.sleep(2000);
				}
				catch(InterruptedException g){
					System.out.println("Interrupted");
				}

				if(choice == 'H' || choice == 'h'){
					if (playerCharging == false){
						//getting the amount of damage the enemy will take and subtracting that from their current health
						int enemyDamage = playerStrength;
						dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
						System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + equippedWeapon.getName());
						System.out.println();
						playerStrength = equippedWeapon.getStrength();
						//checking if the enemy is still alive
						if (dungeonEnemy.getHealth() <= 0){
							didUserWin = true;
							enemyAlive = false;
							return true;
						}

						//printing the enemies health if it is still alive
						else{
							System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch (InterruptedException f){
								System.out.println("Interruputed!");
							}
						}

					}
					else{
						//getting the amount of damage the enemy will take and subtracting that from their current health
						int enemyDamage = playerStrength;
						dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
						System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + equippedWeapon.getName());
						System.out.println();
						playerStrength = equippedWeapon.getStrength();
						//checking if the enemy is still alive
						if (dungeonEnemy.getHealth() <= 0){
							didUserWin = true;
							enemyAlive = false;
							return true;
						}

						//printing the enemies health if it is still alive
						else{
							System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch (InterruptedException f){
								System.out.println("Interruputed!");
							}
						}

						playerStrength = equippedWeapon.getStrength();
						playerCharging = false;
						System.out.println("After you used your last charged attack, your weapon damage was reset to its original " + playerStrength + " damage.");
						System.out.println();
						try{
							Thread.sleep(2500);
						}
						catch(InterruptedException weaponDamageReset){

						}
					}

					//getting players current health before the enemy attacks
					System.out.println("The player currently has a health of " + playerHealth);
					System.out.println();
					try{
						Thread.sleep(2000);
					}
					catch (InterruptedException eee){
						System.out.println("Interrupted!");
					}
					//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
					int playerDamage = ((dungeonEnemy.getDamage()) - (equippedArmor.getStrength()));



					//checking if the players current armor is stronger than the enemy's attack
					if (playerDamage <= 0){
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException y){
							System.out.println("Interrupted");
						}
						System.out.println("The enemy would have done " + dungeonEnemy.getDamage() + " in damage, but your " + equippedArmor.getName() + " completely negated the enemy's attack!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException d){
							System.out.println("Interrupted!");
						}
					}
					//setting the players new health based on the previously calculated enemy damage
					else{
						int currentPlayerHealth = playerHealth;
						int newPlayerHealth = currentPlayerHealth - playerDamage;
						playerHealth = newPlayerHealth;

						System.out.println("The enemies initial attack would have done " + dungeonEnemy.getDamage() + " in damage, but your " + equippedArmor.getName() + " reduced it to " + playerDamage + "!");
						System.out.println();

						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException enemyAttack1){

						}

						System.out.println("You took " + playerDamage + " damage from the enemy!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException c){
							System.out.println("Interrupted!");
						}

						//cheching if the player is still alive
						if (playerHealth <= 0){
							didUserWin = false;
							System.out.println("The enemy brutally murdered you!");
							System.out.println("Better luck next time!");
							try{
								Thread.sleep(3000);
							}
							catch (InterruptedException b){
								System.out.println("Interrupted!");
							}
							System.exit(0);
							System.out.println();
							playerAlive = false;
						}
						else{
							//printing out players health after enemy attack
							System.out.println("After the enemy's latest attack, the player's health is " + playerHealth);
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch(InterruptedException a){
								System.out.println("Interrupted!");
							}
						}


					}
					validInput = true;	
				}
				else if(choice == 'J' || choice == 'j'){
					//getting players current health before the enemy attacks
					System.out.println("The player currently has a health of " + playerHealth);
					System.out.println();
					try{
						Thread.sleep(2000);
					}
					catch (InterruptedException eee){
						System.out.println("Interrupted!");
					}
					//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
					int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef + 5));

					//checking if the players current armor is stronger than the enemy's attack
					if (playerDamage <= 0){
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException y){
							System.out.println("Interrupted");
						}
						
						System.out.println("The enemy would have done " + dungeonEnemy.getDamage() + " in damage, but your " + equippedArmor.getName() + " and defending completely negated the enemy's attack!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException d){
							System.out.println("Interrupted!");
						}

						System.out.println("The enemy currently has a health of " + dungeonEnemy.getHealth() + ".");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException enemyHealthDefense){

						}
						int defendDamage = (dungeonEnemy.getDamage() / 3);
						dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - defendDamage);
						System.out.println("You counter attacked and did " + defendDamage + " damage to the enemy!");
                                                System.out.println();
                                                try{
                                                        Thread.sleep(2000);
                                                }
                                                catch (InterruptedException c){
                                                        System.out.println("Interrupted!");
                                                }
                                                //checking if the enemy is still alive
                                                if (dungeonEnemy.getHealth() <= 0){
                                                        didUserWin = true;
                                                        enemyAlive = false;
                                                        return true;
                                                }//printing the enemies health if it is still alive
                                                else{
                                                        System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
                                                        System.out.println();
                                                        try{
                                                                Thread.sleep(2000);
                                                        }
                                                        catch (InterruptedException f){
                                                                System.out.println("Interruputed!");
                                                        }
                                                }

					}
					//setting the players new health based on the previously calculated enemy damage
					else{
						int currentPlayerHealth = playerHealth;
						int newPlayerHealth = currentPlayerHealth - playerDamage;
						playerHealth = newPlayerHealth;
						int enemyDamage = (dungeonEnemy.getDamage() / 3);
	
						System.out.println("The enemies initial attack would have done " + dungeonEnemy.getDamage() + " in damage,  but your " + equippedArmor.getName() + " and defending reduced it to " + playerDamage + "!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException enemyAttack2){

						}

						System.out.println("You took " + playerDamage + " damage from the enemy!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException noMoreDefense){

						}
						dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);

						if (playerHealth <= 0){
							didUserWin = false;
							System.out.println("The enemy brutally murdered you!");
							System.out.println("Better luck next time!");
							try{
								Thread.sleep(3000);
							}
							catch (InterruptedException b){
								System.out.println("Interrupted!");
							}
							System.exit(0);
							System.out.println();
							playerAlive = false;
						}
						else{
							//printing out players health after enemy attack
							System.out.println("After the enemy's latest attack, the player's health is " + playerHealth);
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch(InterruptedException a){
								System.out.println("Interrupted!");
							}
						}

						System.out.println("You counter attacked and did " + enemyDamage + " damage to the enemy!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException c){
							System.out.println("Interrupted!");
						}
						//checking if the enemy is still alive
						if (dungeonEnemy.getHealth() <= 0){
							didUserWin = true;
							enemyAlive = false;
							return true;
						}//printing the enemies health if it is still alive
						else{
							System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch (InterruptedException f){
								System.out.println("Interruputed!");
							}
						}
						//cheching if the player is still alive

					}
					validInput = true;
				}

				else if(choice == 'K' || choice == 'k'){
					playerStrength = (playerStrength * 2);
					playerCharging = true;
					//checking if the enemy is still alive
					if (dungeonEnemy.getHealth() <= 0){
						didUserWin = true;
						enemyAlive = false;
						return true;
					}
					System.out.println("You are charging your attack!");
					System.out.println("If you attacked next round, your " + equippedWeapon.getName() + " would do " + playerStrength + " in damage!");
					try{
						Thread.sleep(2000);
					}
					catch(InterruptedException chargingAttack){

					}
					//getting players current health before the enemy attacks
					System.out.println("The player currently has a health of " + playerHealth);
					System.out.println();
					try{
						Thread.sleep(2000);
					}
					catch (InterruptedException eee){
						System.out.println("Interrupted!");
					}
					//getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
					int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef));

					//checking if the players current armor is stronger than the enemy's attack
					if (playerDamage <= 0){
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException y){
							System.out.println("Interrupted");
						}
						System.out.println("The enemy would have done " + dungeonEnemy.getDamage() + "in damage, but your " + equippedArmor.getName() + " completely negated the enemy's attack!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException d){
							System.out.println("Interrupted!");
						}
					}
					//setting the players new health based on the previously calculated enemy damage
					else{
						int currentPlayerHealth = playerHealth;
						int newPlayerHealth = currentPlayerHealth - playerDamage;
						playerHealth = newPlayerHealth;


						System.out.println("The enemies initial attack would have done " + dungeonEnemy.getDamage() + " in damage, but your " + equippedArmor.getName() + " reduced it to " + playerDamage + "!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch(InterruptedException enemyAttack3){

						}

						System.out.println("You took " + playerDamage + " damage from the enemy!");
						System.out.println();
						try{
							Thread.sleep(2000);
						}
						catch (InterruptedException c){
							System.out.println("Interrupted!");
						}

						//cheching if the player is still alive
						if (playerHealth <= 0){
							didUserWin = false;
							System.out.println("The enemy brutally murdered you!");
							System.out.println("Better luck next time!");
							try{
								Thread.sleep(3000);
							}
							catch (InterruptedException b){
								System.out.println("Interrupted!");
							}
							System.exit(0);
							System.out.println();
							playerAlive = false;
						}
						else{
							//printing out players health after enemy attack
							System.out.println("After the enemy's latest attack, the player's health is " + playerHealth);
							System.out.println();
							try{
								Thread.sleep(2000);
							}
							catch(InterruptedException a){
								System.out.println("Interrupted!");
							}
						}
					}
					validInput = true;

				}
				else{
					System.out.println("Thats not a choice! Try again!");
					validInput = false;
				}
			}
			roundNumber = roundNumber + 1;
		}
		return didUserWin;
	}
}
