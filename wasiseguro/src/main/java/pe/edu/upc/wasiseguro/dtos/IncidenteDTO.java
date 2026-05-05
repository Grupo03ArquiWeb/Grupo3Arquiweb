    package pe.edu.upc.wasiseguro.dtos;

    import java.time.OffsetDateTime;
    import java.util.UUID;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;

    public class IncidenteDTO {
        private UUID id;
        private UUID idUsuario;
        private int idTipo;
        private int idZona;

        @NotBlank(message = "La descripción del riesgo es obligatoria")
        private String descripcion;

        @NotNull(message = "Debe marcar la latitud en el mapa")
        private Double latitud;

        @NotNull(message = "Debe marcar la longitud en el mapa")
        private Double longitud;
        private String fotoUrl;
        private OffsetDateTime fechaOcurrido;
        @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
        public String getFechaFormateada() {
            if (this.fechaOcurrido == null) return null;
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' hh:mm a")
                    .withZone(java.time.ZoneId.of("America/Lima"));
            return this.fechaOcurrido.format(formatter);
        }
        private String estado;
        private int votosValido;
        private int votosInvalido;

        private boolean esAnonimo;

        @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
        public String getAutorVisible() {
            if (this.esAnonimo) {
                return "Usuario Anónimo";
            }
            return (this.idUsuario != null) ? this.idUsuario.toString() : "No asignado";
        }

        public boolean isEsAnonimo() { return esAnonimo; }
        public void setEsAnonimo(boolean esAnonimo) { this.esAnonimo = esAnonimo; }

        public IncidenteDTO() {}

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }

        public UUID getIdUsuario() { return idUsuario; }
        public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }

        public int getIdTipo() { return idTipo; }
        public void setIdTipo(int idTipo) { this.idTipo = idTipo; }

        public int getIdZona() { return idZona; }
        public void setIdZona(int idZona) { this.idZona = idZona; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public Double getLatitud() { return latitud; }
        public void setLatitud(Double latitud) { this.latitud = latitud; }

        public Double getLongitud() { return longitud; }
        public void setLongitud(Double longitud) { this.longitud = longitud; }

        public String getFotoUrl() { return fotoUrl; }
        public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

        public OffsetDateTime getFechaOcurrido() { return fechaOcurrido; }
        public void setFechaOcurrido(OffsetDateTime fechaOcurrido) { this.fechaOcurrido = fechaOcurrido; }

        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }

        public int getVotosValido() { return votosValido; }
        public void setVotosValido(int votosValido) { this.votosValido = votosValido; }

        public int getVotosInvalido() { return votosInvalido; }
        public void setVotosInvalido(int votosInvalido) { this.votosInvalido = votosInvalido; }
    }