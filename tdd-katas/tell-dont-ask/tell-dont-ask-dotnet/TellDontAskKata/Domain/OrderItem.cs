﻿namespace TellDontAskKata.Main.Domain
{
    public class OrderItem
    {
        public Product Product { get; set; } = default!;
        public int Quantity { get; set; }
        public decimal TaxedAmount { get; set; }
        public decimal Tax { get; set; }
    }
}
