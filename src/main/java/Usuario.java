import jakarta.persistence.*;

/**
 * Clase Entidad que representa a un Usuario en el sistema.
 * Mapeada a la tabla 't_usuarios'.
 */
@Entity
@Table(name = "t_usuarios")
public class Usuario {

    // Identificador único (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa AUTO_INCREMENT de MySQL
    @Column(name = "user_id") // Opcional: Cambia el nombre de la columna en BD
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombre;

    @Column(unique = true) // No puede haber dos emails iguales
    private String email;

    // --- CONSTRUCTORES ---

    // 1. Constructor vacío (OBLIGATORIO para JPA/Hibernate)
    // Hibernate usa reflexión para instanciar la clase y necesita este constructor.
    public Usuario() {
    }

    // 2. Constructor con datos (para usarlo nosotros cómodamente)
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // --- GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ToString para imprimir el objeto fácilmente en consola
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}