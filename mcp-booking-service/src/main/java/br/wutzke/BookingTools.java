package br.wutzke;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService bookingService;

    @Tool(name="Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId).")
    public String getBookingDetails(@ToolArg(description = "O ID numérico da reserva (ex: 12345)") long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    @Tool(name="""
        Cancela uma reserva existente.
        Para confirmar o cancelamento, é necessário fornecer o ID da reserva (bookingId)
        e o nome do cliente (userName).
    """)
    public String cancelBooking(@ToolArg(description = "ID da reserva a cancelar") long bookingId,
                                @ToolArg(description = "Usuário que está tentando cancelar a reserva") String userName) {
        return bookingService.cancelBooking(bookingId, userName)
                .map(booking -> "Reserva " + bookingId + " cancelada com sucesso. Status atual: " + booking.status())
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID da reserva está correto e se você tem permissão.");
    }

    @Tool(name="Lista os pacotes de viagem disponíveis para uma determinada categoria (ex: ADVENTURE, TREASURES).")
    public String listPackagesByCategory(@ToolArg(description = "categoria a ser usada na busca de pacotes") Category category) {
        List<Booking> packages = bookingService.findPackagesByCategory(category);
        if (packages.isEmpty()) {
            return "Nenhum pacote encontrado para a categoria: " + category;
        }
        return "Pacotes encontrados para a categoria '" + category + "': " + packages.stream()
                .map(Booking::destination)
                .toList().toString();
    }
}
