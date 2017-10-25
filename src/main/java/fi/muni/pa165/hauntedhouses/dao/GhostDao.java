package fi.muni.pa165.hauntedhouses.dao;

import java.util.List;

import fi.muni.pa165.hauntedhouses.entity.Ghost;

/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public interface GhostDao {

    /**
     * Creates a new ghost
     * @param ghost is the ghost to be created
     */
    public void create(Ghost ghost);
    
    /**
     * Removes the existing ghost
     * @param ghost is the ghost to be removed
     */
    public void remove(Ghost ghost);
    
    /**
     * Returns a specific ghost based on its unique ID.
     * @param id is the id of the ghost
     * @return the found ghost, null otherwise
     */
    public Ghost findById(Long id);
    
    /**
     * Returns a specific ghost based on its unique name.
     * @param name is the name of the ghost
     * @return the found ghost, null otherwise
     */
    public Ghost findByName(String name);
    
    /**
     * Returns a list of all ghosts.
     * @return the list of all ghosts
     */
    public List<Ghost> findAll();
}
