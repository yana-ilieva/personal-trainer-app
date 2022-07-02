<template>
  <div class="w-full">
    <stripe-checkout
      ref="checkoutRef"
      mode="subscription"
      :pk="publishableKey"
      :line-items="lineItems"
      :success-url="successURL"
      :cancel-url="cancelURL"
      @loading="(v) => (loading = v)"
    />
    <button @click="submit">Subscribe!</button>
  </div>
</template>

<script>
import { StripeCheckout } from "@vue-stripe/vue-stripe";
export default {
  components: {
    StripeCheckout,
  },
  data() {
    return {
      publishableKey:
        "pk_test_51LAeHJHkfVeGVtbeRXdUeXdofF9IiyUA6f3g6csIY5Gf3n3ymvz3w29MXkCc7LqM3Q5yNCD8PqoQlR6si26O6QW400emh36S69",
      loading: false,
      lineItems: [
        {
          price: "price_1LGmhwHkfVeGVtbey9REKcF9", // The id of the recurring price you created in your Stripe dashboard
          quantity: 1,
        },
      ],
      successURL: window.location.origin,
      cancelURL: window.location.origin,
    };
  },
  methods: {
    submit() {
      // You will be redirected to Stripe's secure checkout page
      this.$refs.checkoutRef.redirectToCheckout();
    },
  },
};
</script>
