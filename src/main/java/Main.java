import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // 1. OBTENER LA FACTORÍA DE ENTITY MANAGER
        // El nombre "miUnidadPersistencia" debe coincidir EXACTAMENTE con el persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

        // El EntityManager es el gestor principal (nuestra conexión activa)
        EntityManager em = emf.createEntityManager();

        try {
            // 2. INICIAR TRANSACCIÓN
            // En bases de datos, las modificaciones deben ser atómicas
            em.getTransaction().begin();

            // 3. CREAR OBJETOS (Estado: Transient - No guardado aún)
            System.out.println("Creando usuarios en memoria...");
            Usuario u1 = new Usuario("Ana Garcia", "ana.garcia@example.com");
            Usuario u2 = new Usuario("Luis Ramos", "luis.ramos@example.com");

            // 4. PERSISTIR (Estado: Managed - Hibernate los vigila)
            // Aquí Hibernate prepara el INSERT, pero aún no lo envía obligatoriamente a la BD
            System.out.println("Persistiendo usuarios...");
            em.persist(u1);
            em.persist(u2);

            // 5. COMMIT (Confirmar cambios)
            // Aquí se ejecuta el SQL real y se cierra la transacción
            em.getTransaction().commit();

            System.out.println("¡Usuarios guardados con éxito!");
            System.out.println("ID generado para Ana: " + u1.getId());

        } catch (Exception e) {
            // Si algo falla, deshacemos todo (Rollback)
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // 6. CERRAR RECURSOS
            em.close();
            emf.close();
        }
    }
}