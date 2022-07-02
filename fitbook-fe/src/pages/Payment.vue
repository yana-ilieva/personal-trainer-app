<template>
  <div class="w-full">
    <div class="w-10/12 text-left mx-auto mt-10 bg-white shadow sm:rounded-lg">
      <div class="px-4 py-5 sm:p-6">
        <h3 class="text-lg leading-6 font-medium text-gray-900">
          Trainer Subscription
        </h3>
        <div class="mt-2 sm:flex sm:items-start sm:justify-between">
          <div class="max-w-xl text-sm text-gray-500">
            <p>
              When you click <span class="font-semibold">Subscribe</span> you
              will be redirected to a Stripe page where you need to enter your
              credit card information
            </p>
          </div>
          <div
            class="mt-5 sm:mt-0 sm:ml-6 sm:flex-shrink-0 sm:flex sm:items-center"
          >
            <button
              @click="submit"
              type="button"
              class="inline-flex items-center px-4 py-2 border border-transparent shadow-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:text-sm"
            >
              Subscribe
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { loadStripe } from "@stripe/stripe-js";
export default {
  data() {
    return {
      stripe: null,
      publishableKey:
        "pk_test_51LAeHJHkfVeGVtbeRXdUeXdofF9IiyUA6f3g6csIY5Gf3n3ymvz3w29MXkCc7LqM3Q5yNCD8PqoQlR6si26O6QW400emh36S69",
      loading: false,
      lineItems: [
        {
          price: "", // The id of the recurring price you created in your Stripe dashboard
          quantity: 1,
        },
      ],
      successURL: window.location.origin,
      cancelURL: window.location.origin,
    };
  },
  methods: {
    async getPriceId() {
      const response = await fetch(
        `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        console.log("client: ", responseData);

        const responsePrice = await fetch(
          `http://localhost:8081/api/payment/client/${responseData.id}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
            },
          }
        );
        console.log("response price: ", responsePrice);
        if (responsePrice.ok) {
          return await responsePrice.text();
        } else {
          console.log("error getting user data");
        }
      } else {
        console.log("error getting user data");
      }
    },
    submit() {
      // You will be redirected to Stripe's secure checkout page
      this.stripe.redirectToCheckout({
        mode: "subscription",
        lineItems: this.lineItems,
        successUrl: this.successURL,
        cancelUrl: this.cancelURL,
      });
    },
  },
  async mounted() {
    const priceId = await this.getPriceId();
    console.log("price id from api: ", priceId);
    this.lineItems[0].price = priceId;

    this.stripe = await loadStripe(this.publishableKey);
    console.log("stripe loaded: ", this.stripe);
  },
};
</script>
