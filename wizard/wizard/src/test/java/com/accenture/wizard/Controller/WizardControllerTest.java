package com.accenture.wizard.Controller;

import com.accenture.wizard.Entity.*;
import com.accenture.wizard.Service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WizardControllerTest {

    @Mock
    WizardServices wizardServices;

    @Test
    public void testIntro() {
        WizardController controller = new WizardController();

        String actualMessage = controller.intro();
        String expectedMessage = "Welcome!";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testGetWizards() {
        WizardServices mockService = mock(WizardServices.class);
        LocalDate joinedDate = LocalDate.of(2023, 07, 23);
        List<Wizard> wizards = Arrays.asList(new Wizard(2, "Lexus",19,joinedDate,"Y"),
                new Wizard(3, "Mazda", 18,joinedDate,"N"),
                new Wizard(4, "Honda", 20,joinedDate,"Y"));
        when(mockService.getWizards()).thenReturn(wizards);

        WizardController controller = new WizardController();
        controller.service = mockService;

        List<Wizard> result = controller.getWizards();
        assertEquals(3, result.size());
        assertEquals(2, result.get(0).getWizardID());
        assertEquals("Lexus", result.get(0).getWizardName());
        assertEquals(19, result.get(0).getWizardAge());
        assertEquals(joinedDate, result.get(0).getJoinedDate());
        assertEquals("Y", result.get(0).getIsActive());
    }
    @Test
    public void testAddWizardSuccess() {
        WizardServices mockService = mock(WizardServices.class);
        WizardController controller = new WizardController();
        controller.service = mockService;

        Wizard wizard = new Wizard();
        wizard.setWizardID(1);
        wizard.setWizardName("Harry Potter");
        wizard.setWizardAge(20);
        wizard.setJoinedDate(LocalDate.of(1980, 7, 31));
        wizard.setIsActive("N");

        when(mockService.addWizard(wizard)).thenReturn(wizard);
        Wizard result = controller.addWizard(wizard);
        assertEquals(wizard, result);
    }
    @Test
    public void testDeleteWizard() {
        WizardServices mockService = mock(WizardServices.class);
        WizardController controller = new WizardController();
        controller.service = mockService;

        int wizardID = 1;
        controller.deleteWizard(wizardID);
        verify(mockService, times(1)).deleteWizard(wizardID);

        assertEquals("Wizard with ID 1 is deleted", controller.deleteWizard(wizardID));
    }

    @Test
    public void testUpdateWizard() {
        WizardServices mockService = mock(WizardServices.class);
        WizardController controller = new WizardController();
        controller.service = mockService;

        Wizard wizard = new Wizard();
        wizard.setWizardID(1);
        wizard.setWizardName("Harry Potter");
        wizard.setWizardAge(20);
        wizard.setJoinedDate(LocalDate.of(1980, 7, 31));
        wizard.setIsActive("N");

        controller.updateWizard(wizard);
        verify(mockService, times(1)).updateWizard(wizard);
    }
}

