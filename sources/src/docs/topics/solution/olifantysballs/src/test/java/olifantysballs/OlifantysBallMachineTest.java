package olifantysballs;

import util.StringOutput;
import java.io.PrintStream;
//import static org.hamcrest.CoreMatchers.containsString;
//import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
//import org.junit.Ignore;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static olifantysballs.StateEnum.NO_COIN;
import static olifantysballs.StateEnum.HAS_COIN;
import static olifantysballs.StateEnum.SOLD_OUT;
import static olifantysballs.StateEnum.WINNER;

/**
 * Test class for Ball Machine. Method names should say it all.
 *
 * Any mocks are set up per test.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
//@Disabled
public class OlifantysBallMachineTest {

    OlifantysBallMachine instance = (OlifantysBallMachine) GumBallAPI.createMachine();

    //<editor-fold defaultstate="expanded" desc="TASK_1B1; __STUDENT_ID__; WEIGHT 10;">
    /**
     * Fill with enough balls, insert coin and draw repeatedly until empty.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void get_some_balls() {
        //Start Solution::replacewith:://TODO 1B1 test what happens when we have balls or not
        instance.refill( 2 );
        assertThat( instance.getState() ).as( "start state" ).isSameAs(NO_COIN );
        System.out.println( "get a tasty ball" );
        instance.insertCoin();
        assertThat( instance.getState() ).as( "start state" ).isSameAs(HAS_COIN );
        instance.draw();
        instance.insertCoin();
        instance.draw();
        assertThat( instance.isEmpty() ).isTrue();
        //End Solution::replacewith::fail("test not implemented");
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1B2; __STUDENT_ID__; WEIGHT 10;">
    //@Disabled( "Think TDD" )
    @Test
    public void gimme_my_money_back() {
        //Start Solution::replacewith:://TODO 1B2 test what happens on eject coin
        System.out.println( "I want my money back" );
        instance.ejectCoin(); // impatient bugger, helps coverage.
        instance.refill( 10 );
        instance.insertCoin();
        assertThat( instance.getState() ).isSameAs(HAS_COIN );
        instance.ejectCoin();
        assertThat( instance.getState() ).as( "start state" ).isSameAs(NO_COIN );
        instance.ejectCoin();
        assertThat( instance.getState() ).as( "start state" ).isSameAs(NO_COIN );
        //End Solution::replacewith::fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Fill in three portions. Should go from SoldOut to NoCoin and stay there.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void fillup() {
        assertThat( instance.getState() ).as( "Starts in Sold out" ).isSameAs(SOLD_OUT );
        instance.refill( 2 );
        assertThat( instance.getState() ).as( "Not in waiting for coin" ).isSameAs(NO_COIN );
        instance.insertCoin(); // addBalls in HAS_COIN state for coverage
        assertThat( instance.getState() ).as( "Should be in HasCoin" ).isSameAs(HAS_COIN );
        instance.refill( 2 );
        assertThat( instance.getState() ).as( "Should stay in HasCoin" ).isSameAs(HAS_COIN );
        instance.refill( 2 );
        assertThat( instance.getBallCount() ).as( "Enough for three winners " ).isEqualTo( 6 );
    }

    /**
     * Use a spy to monitor what happens in a instance and if indeed the proper
     * methods a are being called. We call the instance buggedInstance to show
     * that it is being watched.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void make_me_rich_aka_try_insert_coin_in_every_state() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        OlifantysBallMachine buggedInstance = Mockito.spy( m ); // <1>
        buggedInstance.refill( 5 );
        buggedInstance.insertCoin(); // <2>
        buggedInstance.insertCoin(); // <3>
        verify( buggedInstance, times( 1 ) ).changeState(HAS_COIN ); // <4>
        assertThat( buggedInstance.getState() )
                .as( "should be stubbornly staying in HasCoin" ).isSameAs(HAS_COIN ); // <5>
    }

    /**
     * For coverage, test with zero and one ball(s), to see the plural s coming
     * and going. For coverage.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void tostring_of_empty_machine() {
        // for coverage
        OlifantysBallMachine m = new OlifantysBallMachine();
        assertThat( m.toString().contains( "gumballs" ) ).isTrue();
        m.addBalls( 1 );
        assertThat( m.toString().contains( "gumballs" ) ).isFalse();
    }

    /**
     * IsWinner uses a random generator with a 10% lucky chance. Try to become a
     * winner for some acceptable testing time.
     */
    //@Disabled( "Think TDD" )
    @Test//( timeout = 200 )
    @Timeout( 200 )
    public void wait_for_winner() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        m.addBalls( 1 );
        boolean succes = false;
        while ( true ) {
            if ( succes |= m.isWinner() ) {
                break;
            }
        }
        assertThat( succes ).as( "perseverance wins" ).isTrue();
    }

    //@Disabled( "Think TDD" )
    @Test
    public void empty_machine_has_no_winner() {
        OlifantysBallMachine m = new OlifantysBallMachine();
        assertThat( m.isWinner() ).as( "no winners here" ).isFalse();
    }

    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void ignored_events_in_has_coin() {
        instance.changeState(HAS_COIN );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::insertCoin, // void()
                () -> {
                    spy.addBalls( 12 );
                } // wrapped in runnable
        );
    }

    //<editor-fold defaultstate="expanded" desc="TASK_1B3; __STUDENT_ID__; WEIGHT 10;">
    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void ignored_events_in_soldout() {
        //Start Solution::replacewith:://TODO 1B3 test that soldout is stubborn
        instance.changeState(SOLD_OUT );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::insertCoin,
                spy::draw,
                spy::ejectCoin );
        //End Solution::replacewith::fail("test not implemented");
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1B4; __STUDENT_ID__; WEIGHT 10;">
    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void ignored_events_in_nocoin() {
        //Start Solution::replacewith:://TODO 1B4 test that nocoin insists on coins and nothing else
        this.instance.changeState(NO_COIN );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        assertNoStateChange( spy,
                spy::ejectCoin,
                spy::draw,
                () -> {
                    spy.addBalls( 2 );
                } );
        //End Solution::replacewith::fail("test not implemented");
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1B5; __STUDENT_ID__; WEIGHT 10;">
    /**
     * Test that methods that should not result in a state change indeed do not.
     * Use a helper method to invoke the methods as Runnable.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void ignored_events_in_winner() {
        //Start Solution::replacewith:://TODO 1B5 test is winner state
        instance.changeState(WINNER );
        OlifantysBallMachine spy = Mockito.spy( this.instance );
        System.out.println( "sintance= " + spy.getClass().
                getCanonicalName() );
        assertNoStateChangeSpied( spy,
                spy::insertCoin,
                spy::ejectCoin,
                () -> {
                    spy.addBalls( 2 );
                }
        );
        //End Solution::replacewith::fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Make sure that the context stick with trhe design contract, calling exit
     * and enter on state change.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void testChangeState() {
        State state1 = Mockito.mock( State.class );
        State state2 = Mockito.mock( State.class );
        OlifantysBallMachine ctx = new OlifantysBallMachine();
        ctx.changeState( state1 );
        verify( state1, times( 1 ) ).enter( ctx );
        verify( state2, never() ).enter( ctx );

        ctx.changeState( state2 );
        verify( state1, times( 1 ) ).exit( ctx );
        verify( state2, times( 1 ) ).enter( ctx );
    }

    //@Disabled( "Think TDD" )
    @Test
    public void testPrintToOutput() {
        OlifantysBallMachine ctx = new OlifantysBallMachine();
        assertThat( ctx.getOutput() ).isSameAs( System.out );
        StringOutput sout = new StringOutput();
        PrintStream out = sout.asPrintStream();
        ctx.setOutput( out );
        ctx.refill( 2 );
        ctx.insertCoin();
        ctx.draw();
        assertThat( sout.toString() ).contains( "OlifantysGumball" );

    }

    /**
     * Helper to invoke methods on context that should not change from its
     * initial state. This method only tests if the state before and after are
     * the same, which is is does not ensure that the machine changed to through
     * the actions of the runnable.
     *
     * @param ctx context of the state
     * @param operations list of runnables to be invoked on the instance.
     *
     */
    void assertNoStateChange( Context ctx, Runnable... operations ) {
        OlifantysBallMachine obm = (OlifantysBallMachine) ctx;
        State initial = obm.getState();
        for ( Runnable op : operations ) {
            op.run();
            assertThat( obm.getState() )
                    .as( "invocation effected state change" )
                    .isSameAs( initial );
        }
    }

    /**
     * Helper to invoke methods on context that should not change from its
     * initial state.
     *
     * When using this method, make sure you start spying on the context AFTER
     * you moved it to the proper state. Otherwise the spy will rightfully fail
     * because a set or change state did happen.
     *
     * @param ctx spied (mocked) upon context
     * @param operations list of runnables to be invoked on the instance.
     *
     * @throws org.mockito.exceptions.misusing.NotAMockException when ctx is not
     * a mock as in not spied upon. Consider this a error.
     */
    void assertNoStateChangeSpied( Context ctx, Runnable... operations ) {
        OlifantysBallMachine obm = (OlifantysBallMachine) ctx;
        State initial = obm.getState();
        for ( Runnable op : operations ) {
            op.run();
            verify( ctx, never() ).changeState( any() );
            assertThat( obm.getState() )
                    .as( "invocation effected state change" )
                    .isSameAs( initial );
        }
    }
}
