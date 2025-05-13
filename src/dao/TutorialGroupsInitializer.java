package dao;

import adt.*;
import entity.TutorialGroups;

public class TutorialGroupsInitializer {

    public UniversalInterface<TutorialGroups> initializeTutorialGroups() {
        UniversalInterface<TutorialGroups> pList = new UniversalList<>();
        pList.add(new TutorialGroups("BSEG1"));
        pList.add(new TutorialGroups("BSEG2"));
        pList.add(new TutorialGroups("BISEG1"));
        pList.add(new TutorialGroups("BISEG2"));
        pList.add(new TutorialGroups("BITG1"));
        pList.add(new TutorialGroups("BITG2"));
        pList.add(new TutorialGroups("BCSIG1"));
        pList.add(new TutorialGroups("BCSIG2"));
        pList.add(new TutorialGroups("BCSDG1"));
        pList.add(new TutorialGroups("BCSDG2"));
        

        TutorialGroupsDAO tutorialGroupsDAO = new TutorialGroupsDAO();
        tutorialGroupsDAO.saveToFile(pList);
        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        TutorialGroupsInitializer g = new TutorialGroupsInitializer();
        UniversalInterface<TutorialGroups> tutorialGroupsList = g.initializeTutorialGroups();
        System.out.println("\nTutorial Groups:\n" + tutorialGroupsList);
    }

}
