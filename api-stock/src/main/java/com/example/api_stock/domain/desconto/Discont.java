package com.example.api_stock.domain.desconto;

import com.example.api_stock.error.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discont {
    private Double value;
    private DiscountType type;

    public Discont(Double value, String type) {
        this.value = value;
        this.type = DiscountType.valueOf(type.toUpperCase());
    }

    public double applyDiscount(double total) {
        if (value == null || value <= 0 || type == null) return total;

        double result;

        switch (type) {
            case PERCENT:
                if (value > 100) throw new BusinessException("Desconto inválido.");
                result = total - (total * (value / 100));
                break;

            case FIXED:
                if (value > total) throw new BusinessException("Desconto maior que o total.");
                result = total - value;
                break;

            default:
                throw new BusinessException("Tipo de Desconto inválido.");
        }

        return BigDecimal.valueOf(result)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
