## combined controls
```
gp2.dpad_up: high bucket
- slides up
- bar parallel
- wrist up
gp2.b: wall pickup
- wrist wall
- bar wall
gp2.x: pit pickup
- bar ground
gp2.dpad_right: low bucket
- medium slides
- parallel bar
- wrist up
gp2.y: high clip
- bar set to CLIP state
- wrist ground
gp1 triggers: wrist move

```
## all controls
### Claw.java
```
Servo "claw1"
gp1.left_bumper: OPEN
gp1.right_bumper: CLOSE
default: OPEN
```
### driveTrain.java
```
DcMotor "frontRight"
DcMotor "frontLeft"
DcMotor "backRight"
DcMotor "backLeft"
gp1.left_stick_x, gp1.left_stick_y, gp1.right_stick_x: Drivetrain movement
// gp1.a: slowmode
```
### Slides.java
```
DcMotor "slideLeft"
DcMotor "slideRight"
gp2.dpad_down: LOW
gp2.dpad_up: HIGH
gp2.dpad_right: MED
```
### Intake.java
UNUSED, used for training bot testing
```
DcMotor "intake1"
DcMotor "intake2"
gp1.a: 0.8
gp1.b: -0.8
default: 0
```
### fourBar.java
```
DcMotor "fourBar"
gp2.b: WALL
gp2.y: PARALLEL
gp2.x: GROUND
gp2.a: START
```
### Wrist.java
```
Servo "wristServo"
gp1.a: GROUND
gp1.b: HIGH
gp1.y: WALL
```
Diagram:
```
PLAYER 1:
         LT: Wrist Down                                                  RT: Wrist Up
        LB: Claw close                                                  RB: Claw close

           D-up: ___                                                           Y/Δ: ___

D-Left: ___            D-Right: ___                                 X/□: ___       B/◯: ___

         D-Down: ___                                           A/X: SlowMode


                               Left stick:           Right Stick: 
                             D-Train F,L,B,R          D-Train Turn



PLAYER 2:

           LT:___                                                             RT:___ 
          LB:___                                                            RB:___ 

        D-up: High Bucket                                                Y/Δ: High CLip

D-Left: ___        D-Right: Low Bucket                     X/□: Pit Pickup       B/◯: Wall Pickup

         D-Down: NONE                                         A/X: fourbar start


                          Left stick:___           Right Stick:___ 




      _=====_                                   _=====_
     / _____ \                                 / _____ \
   +.-'_____'-.--------------------------------.-'_____'-.+
   /   |     |  '.          S O N Y          .'  |  _  |   \
  / ___| /|\ |___ \                         / ___| /_\ |___ \
 / |      |      | ;  __                 _   ; | _         _ |
| | <---   ---> | | |__|               |_:> | ||_|        (_)|
| |___   |   ___| ;SELECT                    ; |___       ___|
|\    | \|/ |    /  _     ___           _   \    | (X) |    /
| \   |_____|  .','" "', |___|     ,'" "', '.  |_____|  .' |
|  '-.______.-' /       \ANALOG    /       \  '-._____.-'  |
|               |       |---------|       |                |
|              /\       /         \       /\               |
|             /  '.___.'           '.___.'  \              |
|            /                               \             |
 \          /                                  \           /
  \________/                                    \_________/
```