package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockTorpedoStoreOne;
  private TorpedoStore mockTorpedoStoreTwo;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockTorpedoStoreOne = mock(TorpedoStore.class);
    mockTorpedoStoreTwo = mock(TorpedoStore.class); 
    this.ship = new GT4500(mockTorpedoStoreOne, mockTorpedoStoreTwo);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTorpedoStoreOne.fire(1)).thenReturn(true);

    // Act

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mockTorpedoStoreOne, times(1)).fire(1);

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTorpedoStoreOne.fire(1)).thenReturn(true);
    when(mockTorpedoStoreTwo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTorpedoStoreOne, times(1)).fire(1);
    verify(mockTorpedoStoreTwo, times(1)).fire(1);
  }

  @Test
  public void fireTorpero_Single_Error(){
       // Arrange
    when(mockTorpedoStoreOne.fire(1)).thenReturn(false);

    // Act

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mockTorpedoStoreOne, times(1)).fire(1);
  }

  @Test
  public void fireTorpero_Single_FirstEmpty(){
       // Arrange
    when(mockTorpedoStoreOne.fire(1)).thenReturn(false);
    when(mockTorpedoStoreOne.isEmpty()).thenReturn(true);
    when(mockTorpedoStoreTwo.fire(1)).thenReturn(false);

    // Act

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mockTorpedoStoreOne, times(0)).fire(1);
    verify(mockTorpedoStoreTwo, times(1)).fire(1);
  }

}
