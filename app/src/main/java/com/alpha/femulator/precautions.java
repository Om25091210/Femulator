package com.alpha.femulator;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class precautions extends Fragment {

    View view;
    String val;
    ImageView img;
    private Context contextNullSafe;
    TextView head,body;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_precautions, container, false);
        if (contextNullSafe == null) getContextNullSafety();

        img=view.findViewById(R.id.imageView6);
        head=view.findViewById(R.id.textView5);
        body=view.findViewById(R.id.body);

        view.findViewById(R.id.imageView4).setOnClickListener(v->{
            back();
        });
        if(getArguments()!=null) {
            val = getArguments().getString("content");
        }

        if(val.equals("shower")){//ic_shower
            img.setImageResource(R.drawable.ic_shower);
            head.setText(R.string.shower_head);
            body.setText(R.string.shower_body);
        }
        else if(val.equals("pads")){
            img.setImageResource(R.drawable.ic_change_pad);
            head.setText(R.string.pads_head);
            body.setText(R.string.pads_body);
        }
        else if(val.equals("ex")){
            img.setImageResource(R.drawable.ic_ex);
            head.setText(R.string.excercise_head);
            body.setText(R.string.excercise_body);
        }
        else if(val.equals("gentle")){
            img.setImageResource(R.drawable.ic_gentle);
            head.setText("The Art of Being Gentle: Navigating Your Partner's Periods with Grace.");
            body.setText("Our bodies are complex and multifaceted, and as such, they often require extra care and attention. This is especially true during a woman's menstrual cycle, when her body undergoes a range of changes that can cause discomfort, pain, and emotional upheaval. As her partner, it's important to approach this time with sensitivity and compassion, and to strive to be gentle in all of your interactions.\n" +
                    "\n" +
                    "Being gentle doesn't just mean avoiding physical touch or being extra careful with your words (although both of these things are important). It also means taking the time to truly listen to your partner and understand her needs, even when she may not be able to articulate them clearly. It means being patient when she's feeling moody or irritable, and offering words of comfort and reassurance when she's feeling overwhelmed.\n" +
                    "\n" +
                    "One of the most important things you can do to be gentle during your partner's period is to create a safe and supportive environment for her. This may mean running a hot bath or preparing her favorite comfort foods, or simply being present and attentive when she needs someone to talk to.\n" +
                    "\n" +
                    "Remember, your partner's menstrual cycle is a natural and essential part of her reproductive health. By approaching this time with empathy and care, you can deepen your connection and strengthen your relationship in ways that will benefit both of you in the long run. So go ahead, embrace the art of being gentle, and watch as your relationship blossoms and thrives.");
        }
        else if(val.equals("cook")){
            img.setImageResource(R.drawable.ic_cook);
            head.setText("Nourishing Your Partner's Body and Soul: Cooking During Her Periods.");
            body.setText("There's something undeniably comforting about a warm and nourishing meal, especially during a time of physical and emotional upheaval. As your partner navigates her menstrual cycle, cooking for her can be a powerful way to show your love and support, and to help her feel nurtured and cared for.\n" +
                    "\n" +
                    "But cooking during your partner's period isn't just about throwing together any old meal. It's about taking the time to understand her unique needs and cravings, and creating dishes that will nourish her body and soul in equal measure. This might mean incorporating ingredients that are high in iron or other nutrients that support menstrual health, or simply preparing her favorite comfort foods that bring her joy and comfort.\n" +
                    "\n" +
                    "In addition to the physical benefits of a well-prepared meal, cooking for your partner during her period can also offer emotional benefits. It can provide a sense of stability and routine during a time that can feel unpredictable and chaotic, and can offer a tangible expression of your love and care.\n" +
                    "\n" +
                    "So the next time your partner is navigating her menstrual cycle, consider the power of cooking as a way to support and nourish her. Whether it's a hearty soup, a comforting casserole, or a batch of her favorite cookies, your efforts will be sure to make a meaningful impact on her overall wellbeing.");
        }
        else if(val.equals("listen")){
            img.setImageResource(R.drawable.ic_listen);
            head.setText("The Healing Power of Listening: Supporting Your Partner During Her Periods.");
            body.setText("Communication is the cornerstone of any healthy relationship, but during a woman's menstrual cycle, listening takes on an even greater importance. As your partner navigates the physical and emotional changes that come with menstruation, being a compassionate and attentive listener can help her feel heard, understood, and supported in ways that can make all the difference.\n" +
                    "\n" +
                    "Listening during your partner's period means more than just nodding along as she talks. It means taking the time to truly understand her experience, to empathize with her struggles, and to offer a safe and supportive space for her to express her feelings. It means being patient and attentive, even when her emotions may be running high, and offering words of comfort and reassurance when she needs them most.\n" +
                    "\n" +
                    "But listening during your partner's period isn't just about providing a sounding board for her thoughts and feelings. It's also about actively engaging in her experience, asking questions, and seeking to understand the nuances and complexities of her unique journey. By doing so, you can deepen your connection, strengthen your empathy, and support her in ways that will have a lasting impact on your relationship.\n" +
                    "\n" +
                    "So the next time your partner is navigating her menstrual cycle, remember the healing power of listening. By offering your full presence and attention, you can create a safe and supportive space for her to express herself and find comfort and solace in your loving embrace.");
        }
        else if(val.equals("atten")){
            img.setImageResource(R.drawable.ic_atten);
            head.setText("Attentive Care: Nurturing Your Partner During Her Menstrual Cycle.");
            body.setText("The menstrual cycle can be a challenging time for many women, with physical discomfort, hormonal changes, and emotional fluctuations all contributing to a range of different symptoms. But with the right care and attention, partners can play a critical role in helping women navigate this time with greater ease and comfort.\n" +
                    "\n" +
                    "Being attentive to your partner during her menstrual cycle means being present, engaged, and proactive in supporting her needs. It means taking the time to understand her symptoms, offering comfort and reassurance when she needs it most, and being proactive in finding ways to ease her discomfort.\n" +
                    "\n" +
                    "This might mean preparing nourishing meals that support her nutritional needs, offering a massage to help ease cramps, or simply being there to listen and offer support when she's feeling overwhelmed or stressed.\n" +
                    "\n" +
                    "But being attentive during your partner's menstrual cycle isn't just about addressing physical symptoms. It's also about creating a safe and supportive emotional environment that allows her to feel seen, heard, and valued. It's about recognizing the unique challenges that come with this time, and being there to offer love, support, and care in ways that will help her feel nurtured and loved.\n" +
                    "\n" +
                    "So the next time your partner is navigating her menstrual cycle, remember the power of attentive care. By being present, proactive, and compassionate, you can help your partner feel supported, cared for, and cherished during this important time in her reproductive health.");
        }
        else if(val.equals("pat")){
            img.setImageResource(R.drawable.ic_patient);
            head.setText("The Gentle Art of Patience: Supporting Your Partner During Her Menstrual Cycle.");
            body.setText("\n" +
                    "The menstrual cycle is a natural part of a woman's reproductive health, but it can also be a challenging and uncomfortable time. As a partner, one of the most important things you can do to help is to be patient and understanding as your loved one navigates the physical and emotional ups and downs that come with this process.\n" +
                    "\n" +
                    "Being patient during your partner's menstrual cycle means recognizing that she may be experiencing discomfort, fatigue, mood swings, and other symptoms that can make her feel irritable, emotional, or overwhelmed. It means being willing to offer a listening ear, a shoulder to cry on, and a safe and nurturing space where she can express her feelings without judgment.\n" +
                    "\n" +
                    "It also means being willing to take on extra household tasks, offer a comforting touch, or simply sit quietly with your partner when she needs it most. By showing patience and understanding, you can help your partner feel loved, supported, and cared for during this challenging time.\n" +
                    "\n" +
                    "Remember, the menstrual cycle is a natural and important part of your partner's reproductive health. By offering patience, empathy, and gentle care, you can help her navigate this process with greater ease and comfort, and deepen your connection in ways that will benefit your relationship for years to come.");
        }


        OnBackPressedCallback callback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fm=((FragmentActivity) getContextNullSafety()).getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                ft.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        return view;
    }
    private void back(){
        FragmentManager fm=((FragmentActivity) getContextNullSafety()).getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }
        ft.commit();
    }
    /**CALL THIS IF YOU NEED CONTEXT*/
    public Context getContextNullSafety() {
        if (getContext() != null) return getContext();
        if (getActivity() != null) return getActivity();
        if (contextNullSafe != null) return contextNullSafe;
        if (getView() != null && getView().getContext() != null) return getView().getContext();
        if (requireContext() != null) return requireContext();
        if (requireActivity() != null) return requireActivity();
        if (requireView() != null && requireView().getContext() != null)
            return requireView().getContext();

        return null;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextNullSafe = context;
    }
}